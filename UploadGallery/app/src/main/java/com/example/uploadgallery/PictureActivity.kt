package com.example.uploadgallery

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uploadgallery.database.MyDbHelper
import com.example.uploadgallery.databinding.ActivityPictureBinding
import java.io.File

class PictureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPictureBinding
    private lateinit var myDbHelper: MyDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)
        binding.apply {
            val list = myDbHelper.getAllImages()
            img1.setImageURI(Uri.fromFile(File(list[0].path)))

            val byteArray = list[0].image
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            img2.setImageBitmap(bitmap)
        }
    }
}