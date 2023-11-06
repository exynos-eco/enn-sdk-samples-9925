// Copyright (c) 2023 Samsung Electronics Co. LTD. Released under the MIT License.

package com.samsung.perfcompare.fragments

import android.graphics.Bitmap
import android.graphics.ColorSpace
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.samsung.perfcompare.executor.ModelExecutor
import com.samsung.perfcompare.data.ModelConstants
import com.samsung.perfcompare.databinding.FragmentImageBinding

class ImageFragment : Fragment(), ModelExecutor.ExecutorListener {
    private lateinit var binding: FragmentImageBinding
    private lateinit var bitmapBuffer: Bitmap
    private lateinit var modelExecutor: ModelExecutor
    private lateinit var detectedItems1: List<Pair<TextView, TextView>>
    private lateinit var detectedItems2: List<Pair<TextView, TextView>>

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                binding.imageView.setImageURI(it)
                binding.buttonProcess.isEnabled = true

                bitmapBuffer = ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(
                        requireContext().contentResolver, it
                    )
                ) { decoder, _, _ ->
                    decoder.setTargetColorSpace(ColorSpace.get(ColorSpace.Named.SRGB))
                    decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
                    decoder.setTargetSampleSize(1)
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        modelExecutor = ModelExecutor(
            context = requireContext(), executorListener = this
        )

        setUI()
    }

    private fun setUI() {
        binding.buttonLoad.setOnClickListener {
            getContent.launch("image/*")
        }

        binding.buttonProcess.isEnabled = false
        binding.buttonProcess.setOnClickListener {
            process(bitmapBuffer)
        }

        binding.processDataENN.title.text = "ENN"
        binding.processDataTFLite.title.text = "TFLite"

        detectedItems1 = listOf(
            binding.processDataENN.detectedItem0 to binding.processDataENN.detectedItem0Score,
            binding.processDataENN.detectedItem1 to binding.processDataENN.detectedItem1Score,
            binding.processDataENN.detectedItem2 to binding.processDataENN.detectedItem2Score
        )

        detectedItems2 = listOf(
            binding.processDataTFLite.detectedItem0 to binding.processDataTFLite.detectedItem0Score,
            binding.processDataTFLite.detectedItem1 to binding.processDataTFLite.detectedItem1Score,
            binding.processDataTFLite.detectedItem2 to binding.processDataTFLite.detectedItem2Score
        )
    }

    private fun process(bitmapBuffer: Bitmap) {
        modelExecutor.process(processImage(bitmapBuffer))
    }

    private fun processImage(image: Bitmap): Bitmap {
        val croppedImage = createCroppedBitmap(image)

        return Bitmap.createScaledBitmap(
            croppedImage, INPUT_SIZE_W, INPUT_SIZE_H, true
        )
    }

    private fun createCroppedBitmap(image: Bitmap): Bitmap {
        val cropDim = calculateCropDimensions(image)

        return Bitmap.createBitmap(
            image, cropDim[0], cropDim[1], cropDim[2], cropDim[2]
        )
    }

    private fun calculateCropDimensions(image: Bitmap): IntArray {
        return if (image.width > image.height) {
            intArrayOf((image.width - image.height) / 2, 0, image.height)
        } else {
            intArrayOf(0, (image.height - image.width) / 2, image.width)
        }
    }

    override fun onError(error: String) {
        Log.e(TAG, "ModelExecutor error: $error")
    }

    override fun onResults(
        result1: Map<String, Float>,
        inferenceTime1: Long,
        result2: Map<String, Float>,
        inferenceTime2: Long,
        snr: Float
    ) {
        activity?.runOnUiThread {
            binding.processDataENN.inferenceTime.text = "$inferenceTime1 ms"
            binding.processDataTFLite.inferenceTime.text = "$inferenceTime2 ms"
            binding.snrValue.text = "$snr"
            updateUI(result1, result2)
        }
    }

    private fun updateUI(result1: Map<String, Float>, result2: Map<String, Float>) {
        detectedItems1.forEachIndexed { index, pair ->
            if (index < result1.size) {
                val key = result1.keys.elementAt(index)
                pair.first.text = key
                pair.second.text = String.format("%.5f", result1[key])
            } else {
                pair.first.text = ""
                pair.second.text = ""
            }
        }
        detectedItems2.forEachIndexed { index, pair ->
            if (index < result2.size) {
                val key = result2.keys.elementAt(index)
                pair.first.text = key
                pair.second.text = String.format("%.5f", result2[key])
            } else {
                pair.first.text = ""
                pair.second.text = ""
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        modelExecutor.closeENN()
    }

    companion object {
        private const val TAG = "ImageFragment"
        private const val INPUT_SIZE_W = ModelConstants.INPUT_SIZE_W
        private const val INPUT_SIZE_H = ModelConstants.INPUT_SIZE_H
    }
}