package com.example.fandora.ui.donation

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fandora.BuildConfig
import com.example.fandora.databinding.FragmentCameraBinding
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!

    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService

    private val requestCameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            startCamera()
        } else {
            Toast.makeText(requireContext(), "카메라 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            uriToBitmap(it)?.let { bitmap ->
                analyzeImageWithGemini(bitmap)
            } ?: run {
                Toast.makeText(requireContext(), "이미지 로드 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLayout()
        requestPermissionsIfNeeded()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun setLayout() {
        binding.btnCameraBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnCameraCapture.setOnClickListener {
            takePhoto()
        }
        binding.btnCameraAlbum.setOnClickListener {
            openGallery()
        }
    }

    private fun requestPermissionsIfNeeded() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startCamera()
        } else {
            requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.surfaceProvider = binding.previewCamera.surfaceProvider
            }

            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    viewLifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (e: Exception) {
                Log.e("CameraX", "카메라 바인딩 실패: ${e.message}")
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return
        val photoFile = File.createTempFile("album_photo", ".jpg", requireContext().cacheDir)

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e("CameraX", "촬영 실패: ${exc.message}")
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    Log.d("CameraX", "사진 저장 성공: ${photoFile.absolutePath}")
                    val bitmap = BitmapFactory.decodeFile(photoFile.absolutePath)
                    bitmap?.let { analyzeImageWithGemini(it) }
                }
            }
        )
    }

    private fun openGallery() {
        galleryLauncher.launch("image/*")
    }

    private fun uriToBitmap(uri: Uri): Bitmap? {
        return try {
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            Log.e("CameraFragment", "URI를 Bitmap으로 변환 실패: ${e.message}")
            null
        }
    }

    private fun analyzeImageWithGemini(bitmap: Bitmap) {
        lifecycleScope.launch(Dispatchers.IO) {
            val generativeModel = GenerativeModel(
                modelName = "gemini-1.5-pro",
                apiKey = BuildConfig.GEMINI_API_KEY
            )

            try {
                val response = generativeModel.generateContent(content {
                    image(bitmap)
                    text("이 사진의 앨범 제목과 가수명을 알려줘.")
                })

                val textResponse = response.text
                withContext(Dispatchers.Main) {
                    Log.d("Gemini Response", "분석 결과: $textResponse")
                    val action = CameraFragmentDirections.actionCameraToDonationApply()
                    findNavController().navigate(action)
                }

            } catch (e: Exception) {
                Log.e("Gemini Error", "Gemini API 호출 실패: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "이미지 분석 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        cameraExecutor.shutdown()
    }
}