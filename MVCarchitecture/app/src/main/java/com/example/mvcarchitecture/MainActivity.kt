package com.example.mvcarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mvcarchitecture.databinding.ActivityMainBinding
import com.example.mvcarchitecture.model.Model
import java.util.*

class MainActivity : AppCompatActivity(),View.OnClickListener, Observer {

    private lateinit var binding: ActivityMainBinding
    private lateinit var model: Model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = Model()
        model.addObserver(this)

        binding.apply {
            button.setOnClickListener(this@MainActivity)
            button2.setOnClickListener(this@MainActivity)
            button3.setOnClickListener(this@MainActivity)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun update(p0: Observable?, p1: Any?) {
        binding.button.text = model.getValueAtIndex(0).toString()
        binding.button2.text = model.getValueAtIndex(1).toString()
        binding.button3.text = model.getValueAtIndex(2).toString()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button -> {
                model.setValueAtIndex(0)
            }
            R.id.button2 -> {
                model.setValueAtIndex(1)
            }
            R.id.button3 -> {
                model.setValueAtIndex(2)
            }
        }
    }
}