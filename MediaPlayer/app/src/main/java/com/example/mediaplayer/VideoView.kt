package com.example.mediaplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mediaplayer.databinding.ActivityVideoViewBinding

class VideoView : AppCompatActivity() {

    private lateinit var binding: ActivityVideoViewBinding
    var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            playBtn.setOnClickListener {
                videoView.setVideoPath(url)
                videoView.start()
            }
        }
    }
}