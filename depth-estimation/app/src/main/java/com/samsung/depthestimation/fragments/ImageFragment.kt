// Copyright (c) 2023 Samsung Electronics Co. LTD. Released under the MIT License.

package com.samsung.depthestimation.fragments

import android.graphics.Bitmap
import android.graphics.ColorSpace
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.samsung.depthestimation.data.ModelConstants
import com.samsung.depthestimation.databinding.FragmentImageBinding
import com.samsung.depthestimation.executor.ModelExecutor


class ImageFragment : Fragment(), ModelExecutor.ExecutorListener {
    private lateinit var binding: FragmentImageBinding
    private lateinit var bitmapBuffer: Bitmap
    private lateinit var modelExecutor: ModelExecutor

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                binding.imageView.setImageURI(it)
                binding.buttonProcess.isEnabled = true
                binding.overlay.clear()

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
        result: IntArray, inferenceTime: Long
    ) {
        activity?.runOnUiThread {
            binding.processData.inferenceTime.text = "$inferenceTime ms"
            binding.overlay.setResults(
                result, OUTPUT_SIZE_W, OUTPUT_SIZE_H
            )
            binding.overlay.invalidate()
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
        private const val OUTPUT_SIZE_W = ModelConstants.OUTPUT_SIZE_W
        private const val OUTPUT_SIZE_H = ModelConstants.OUTPUT_SIZE_H
    }
}