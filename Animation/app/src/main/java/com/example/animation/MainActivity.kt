package com.example.animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.animation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Animation.AnimationListener {

    private lateinit var binding: ActivityMainBinding
    private var isImage1 = false
    private var isImage2 = false
    private var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            image1.setOnClickListener {
                count++
                isImage1 = true
                val animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.scale1)
                binding.image1.startAnimation(animation)
                animation.setAnimationListener(this@MainActivity)
            }

            image2.setOnClickListener {
                count++
                isImage2 = true
                val animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.scale1)
                binding.image2.startAnimation(animation)
                animation.setAnimationListener(this@MainActivity)
            }
        }
    }

    override fun onAnimationStart(p0: Animation?) {

    }

    override fun onAnimationEnd(p0: Animation?) {
        val animation1 = AnimationUtils.loadAnimation(this, R.anim.scale2)

        if(isImage1) {
            binding.image1.setImageResource(R.drawable.ic_launcher_foreground)
            binding.image1.startAnimation(animation1)
            isImage1 = false
        } else if(isImage2) {
            binding.image2.setImageResource(R.drawable.ic_launcher_foreground)
            binding.image2.startAnimation(animation1)
            isImage2 = false
        }

        animation1.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                if (count == 2) {
                    if (binding.image1.tag == binding.image2.tag) {
                        binding.image1.visibility = View.INVISIBLE
                        binding.image2.visibility = View.INVISIBLE
                    } else {
                        closeImages()
                        count = 0
                    }
                }
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
    }

    override fun onAnimationRepeat(p0: Animation?) {

    }

    private fun closeImages() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.scale1)
        binding.image1.startAnimation(animation)
        binding.image2.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {

                binding.image1.setImageResource(R.drawable.ic_launcher_background)
                binding.image2.setImageResource(R.drawable.ic_launcher_background)
                val animation1 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.scale2)
                binding.image1.startAnimation(animation1)
                binding.image2.startAnimation(animation1)
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
    }
}