package com.example.mvparchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mvparchitecture.databinding.ActivityMainBinding
import com.example.mvparchitecture.models.Model
import com.example.mvparchitecture.presenter.Contract
import com.example.mvparchitecture.presenter.Presenter

class MainActivity : AppCompatActivity(), Contract.View {

    private lateinit var binding: ActivityMainBinding

    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = Presenter(this, Model())
        binding.button.setOnClickListener {
            presenter.onButtonClick()
        }
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    override fun setString(string: String?) {
        binding.textView.text = string
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}