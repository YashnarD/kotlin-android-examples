package com.example.uploadgallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.uploadgallery.database.MyDbHelper
import com.example.uploadgallery.databinding.ActivityGalleryBinding
import com.example.uploadgallery.models.Picture
import java.io.File
import java.io.FileOutputStream

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding
    private val TAG = "GalleryActivity"
    var REQUEST_CODE = 1

    private lateinit var myDbHelper: MyDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)

        binding.apply {
            oldBtn.setOnClickListener {
                oldMethodGallery()
            }
            newBtn.setOnClickListener {
                newMethodGallery()
            }
            deleteBtn.setOnClickListener {
                clearImages()
            }
        }
    }

    private fun clearImages() {
        val filesDir = filesDir
        if(filesDir.isDirectory) {
            val listFiles = filesDir.listFiles()
            for(file in listFiles) {
                file.delete()
            }
        }
    }

    private fun newMethodGallery() {
        getImageContent.launch("image/*")
    }

    private val getImageContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if(uri != null) {
            binding.image.setImageURI(uri)

            val openInputStream = contentResolver?.openInputStream(uri)
            val m = System.currentTimeMillis()
            val file = File(filesDir, "$m.jpg")
            val fileOutputStream = FileOutputStream(file)
            openInputStream?.copyTo(fileOutputStream)
            openInputStream?.close()
            fileOutputStream.close()

            val readBytes = file.readBytes()
            myDbHelper.addImage(Picture(path = file.absolutePath, image = readBytes))
            Log.d(TAG, "onActivityResult: ${file.absolutePath}")
        }
    }

    private fun oldMethodGallery() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val uri = data?.data ?: return
            binding.image.setImageURI(uri)
            val openInputStream = contentResolver?.openInputStream(uri)
            val m = System.currentTimeMillis()
            val file = File(filesDir, "$m.jpg")
            val fileOutputStream = FileOutputStream(file)
            openInputStream?.copyTo(fileOutputStream)
            openInputStream?.close()
            fileOutputStream.close()
            Log.d(TAG, "onActivityResult: ${file.absolutePath}")
        }
    }
}