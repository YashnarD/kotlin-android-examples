package com.example.intent.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            registerTv.setOnClickListener {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                startActivityForResult(intent, 1)
            }
            button1.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+998906772149"))
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val username = data?.getStringExtra("username")
        val password = data?.getStringExtra("password")

        binding.edit1.setText(username)
        binding.edit2.setText(password)
    }
}