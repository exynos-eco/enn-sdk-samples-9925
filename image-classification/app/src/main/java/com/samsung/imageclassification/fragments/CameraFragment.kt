// Copyright (c) 2023 Samsung Electronics Co. LTD. Released under the MIT License.

package com.samsung.imageclassification.fragments

import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.samsung.imageclassification.data.ModelConstants
import com.samsung.imageclassification.databinding.FragmentCameraBinding
import com.samsung.imageclassification.executor.ModelExecutor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class CameraFragment : Fragment(), ModelExecutor.ExecutorListener {
    private lateinit var binding: FragmentCameraBinding
    private lateinit var modelExecutor: ModelExecutor
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var bitmapBuffer: Bitmap
    private lateinit var detectedItems: List<Pair<TextView, TextView>>

    private var camera: Camera? = null
    private var preview: Preview? = null
    private var imageAnalyzer: ImageAnalysis? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        modelExecutor = ModelExecutor(
            context = requireContext(), executorListener = this
        )

        setCamera()
        setUI()
    }

    private fun setCamera() {
        cameraExecutor = Executors.newSingleThreadExecutor()

        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener(
            {
                // Get the ProcessCameraProvider. This is used to bind the lifecycle of cameras to the lifecycle owner
                val cameraProvider = cameraProviderFuture.get()
                // Select the back camera
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                // Set up the preview use case and the image analyzer use case
                setPreview()
                setImageAnalyzer()

                try {
                    // Unbind all use cases before rebinding
                    cameraProvider.unbindAll()
                    // Bind the cameraSelector, preview and imageAnalyzer use cases to the cameraProvider
                    // The camera's lifecycle will be tied to the lifecycle of the fragment
                    camera = cameraProvider.bindToLifecycle(
                        this, cameraSelector, preview, imageAnalyzer
                    )

                    // Connect the preview use case to the viewfinder surface
                    preview?.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                } catch (exc: java.lang.Exception) {
                    Log.e(TAG, "Camera binding failed", exc)
                }
            }, ContextCompat.getMainExecutor(requireContext())
        )
    }

    // Set up the preview for the camera.
    private fun setPreview() {
        preview = Preview.Builder().setTargetRotation(binding.viewFinder.display.rotation).build()
    }

    // Set up the preview
    private fun setImageAnalyzer() {
        // Build an ImageAnalysis instance with the desired configuration
        imageAnalyzer = ImageAnalysis.Builder()
            .setTargetRotation(binding.viewFinder.display.rotation) // Set the target rotation to the current rotation of the viewfinder
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST) // Set the backpressure strategy to keep only the latest image
            .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888) // Set the output image format to RGBA_8888
            .build().also {
                it.setAnalyzer(cameraExecutor) { image -> // Set the analyzer to run on the previously created executor
                    if (!::bitmapBuffer.isInitialized) { // If the bitmapBuffer is not initialized
                        // Create a new bitmap with the same dimensions as the image
                        bitmapBuffer = Bitmap.createBitmap(
                            image.width, image.height, Bitmap.Config.ARGB_8888
                        )
                    }
                    // Process the image
                    process(image)
                }
            }
    }

    // Process the image
    private fun process(image: ImageProxy) {
        image.use { bitmapBuffer.copyPixelsFromBuffer(image.planes[0].buffer) }
        modelExecutor.process(processImage(bitmapBuffer))
    }

    private fun processImage(image: Bitmap): Bitmap {
        val rotatedCroppedImage = createCroppedBitmap(image)

        return Bitmap.createScaledBitmap(
            rotatedCroppedImage, INPUT_SIZE_W, INPUT_SIZE_H, true
        )
    }

    private fun createCroppedBitmap(image: Bitmap): Bitmap {
        val matrix = Matrix().apply { postRotate(90F) }
        val cropDim = calculateCropDimensions(image)

        return Bitmap.createBitmap(
            image, cropDim[0], cropDim[1], cropDim[2], cropDim[2], matrix, true
        )
    }

    private fun calculateCropDimensions(image: Bitmap): IntArray {
        return if (image.width > image.height) {
            intArrayOf((image.width - image.height) / 2, 0, image.height)
        } else {
            intArrayOf(0, (image.height - image.width) / 2, image.width)
        }
    }

    private fun setUI() {
        binding.processData.buttonThresholdPlus.setOnClickListener {
            adjustThreshold(0.1F)
        }

        binding.processData.buttonThresholdMinus.setOnClickListener {
            adjustThreshold(-0.1F)
        }

        detectedItems = listOf(
            binding.processData.detectedItem0 to binding.processData.detectedItem0Score,
            binding.processData.detectedItem1 to binding.processData.detectedItem1Score,
            binding.processData.detectedItem2 to binding.processData.detectedItem2Score
        )

        binding.viewFinder.scaleType = PreviewView.ScaleType.FIT_CENTER
    }

    private fun adjustThreshold(delta: Float) {
        val newThreshold = modelExecutor.threshold + delta

        if (newThreshold in 0.05 .. 0.95) {
            modelExecutor.threshold = newThreshold
            binding.processData.textThreshold.text = String.format("%.1f", newThreshold)
        }
    }

    // Handle errors
    override fun onError(error: String) {
        Log.e(TAG, "ModelExecutor error: $error")
    }

    // Handle results
    override fun onResults(
        result: Map<String, Float>, inferenceTime: Long
    ) {
        activity?.runOnUiThread {
            binding.processData.inferenceTime.text = "$inferenceTime ms"
            updateUI(result)
        }
    }

    private fun updateUI(result: Map<String, Float>) {
        detectedItems.forEachIndexed { index, pair ->
            if (index < result.size) {
                val key = result.keys.elementAt(index)
                pair.first.text = key
                pair.second.text = String.format("%.5f", result[key])
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
        private const val TAG = "CameraFragment"
        private const val INPUT_SIZE_W = ModelConstants.INPUT_SIZE_W
        private const val INPUT_SIZE_H = ModelConstants.INPUT_SIZE_H
    }
}