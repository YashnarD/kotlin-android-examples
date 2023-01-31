package com.example.intent.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intent.R
import com.example.intent.databinding.ActivitySecondBinding
import com.example.intent.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.closeBtn.setOnClickListener {
            finish()
        }
    }
}