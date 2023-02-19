package com.example.paging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.paging3.adapters.PhotoAdapter
import com.example.paging3.databinding.ActivityMainBinding
import com.example.paging3.viewmodels.PhotoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var binding: ActivityMainBinding
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var photoViewModel: PhotoViewModel

    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        job = Job()

        photoAdapter = PhotoAdapter(this)
        binding.rv.adapter = photoAdapter

        photoViewModel = ViewModelProvider(this)[PhotoViewModel::class.java]

        launch {
            photoViewModel.flow.catch {

            }.collect {
                photoAdapter.submitData(it)
            }
        }

    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}