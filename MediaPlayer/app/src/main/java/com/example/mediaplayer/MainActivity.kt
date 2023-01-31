package com.example.mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import com.example.mediaplayer.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener {

    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null
    val url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-9.mp3"
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler(Looper.getMainLooper())

        binding.apply {
            urlBtn.setOnClickListener {
                mediaPlayer = MediaPlayer()
                mediaPlayer?.setDataSource(url)
                mediaPlayer?.setOnPreparedListener(this@MainActivity)
                mediaPlayer?.prepareAsync()
            }

            rawBtn.setOnClickListener {
                mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.kurtlar_vadisi)
                mediaPlayer?.start()

                seekbar.max = mediaPlayer?.duration ?: 0

                handler.postDelayed(runnable, 100)
            }

            pauseBtn.setOnClickListener {
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.pause()
                }
            }

            resumeBtn.setOnClickListener {
                if (mediaPlayer?.isPlaying == false) {
                    mediaPlayer?.start()
                }
            }

            stopBtn.setOnClickListener {
                mediaPlayer?.stop()
            }

            backwardBtn.setOnClickListener {
                mediaPlayer?.seekTo((mediaPlayer?.currentPosition ?: 0).minus(3000))
            }

            forwardBtn.setOnClickListener {
                mediaPlayer?.seekTo((mediaPlayer?.currentPosition ?: 0).plus(3000))
            }

            seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    if (p2) {
                        mediaPlayer?.seekTo(p1)
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }

            })

            checkbox.setOnCheckedChangeListener { compoundButton, b ->
                mediaPlayer?.isLooping = b
            }
        }
    }

    private val runnable = object : Runnable {
        override fun run() {
            val currentPosition = mediaPlayer?.currentPosition
            binding.seekbar.progress = currentPosition ?: 0
            handler.postDelayed(this, 100)
        }

    }

    fun releaseMp() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer?.release()
                mediaPlayer = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMp()
    }
}