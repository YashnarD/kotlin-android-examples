package com.example.intent.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intent.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            registerBtn.setOnClickListener {
                val username = binding.edit1.text.toString()
                val password = binding.edit2.text.toString()

                val intent = Intent()
                intent.putExtra("username", username)
                intent.putExtra("password", password)
                setResult(1, intent)
                finish()
            }
        }
    }
}