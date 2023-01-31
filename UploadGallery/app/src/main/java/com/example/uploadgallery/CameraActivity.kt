package com.example.uploadgallery

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.uploadgallery.databinding.ActivityCameraBinding
import java.io.File
import java.io.IOException
import java.lang.Exception

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding
    private lateinit var currentPhotoPath: String
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            oldBtn.setOnClickListener {
                oldMethodCamera()
            }

            newBtn.setOnClickListener {
                newMethodCamera()
            }

            deleteBtn.setOnClickListener {
                deleteImages()
            }
        }
    }

    private fun deleteImages() {
        val externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (externalFilesDir?.isDirectory == true) {
            val listFiles = externalFilesDir.listFiles()
            for (listFile in listFiles) {
                listFile.delete()
            }
        }
    }

    private fun newMethodCamera() {
        val photoFile = try {
            createImageFile()
        } catch (e: Exception) {
            null
        }
        photoFile?.also {
            val uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, it)
            getCameraImage.launch(uri)
        }
    }

    private val getCameraImage = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        if (it) {
            binding.image.setImageURI(Uri.fromFile(File(currentPhotoPath)))
        }
    }

    private fun oldMethodCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)
        val photoFile = try {
            createImageFile()
        } catch (e: Exception) {
            null
        }
        photoFile?.also {
            val uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, it)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (::currentPhotoPath.isInitialized) {
                binding.image.setImageURI(Uri.fromFile(File(currentPhotoPath)))
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val m = System.currentTimeMillis()
        val externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("G21_$m", ".jpg", externalFilesDir)
            .apply {
                currentPhotoPath = absolutePath
            }
    }
}