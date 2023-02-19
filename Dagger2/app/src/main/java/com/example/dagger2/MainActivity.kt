package com.example.dagger2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dagger2.viewmodels.UserResource
import com.example.dagger2.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    @Inject
    lateinit var userViewModel: UserViewModel

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        setContentView(R.layout.activity_main)

        launch {
            userViewModel.getGithubUsers()
                .collect {
                    when(it) {
                        is UserResource.Loading -> {

                        }
                        is UserResource.Error -> {

                        }
                        is UserResource.Success -> {
                            Log.d(TAG, "onCreate: ${it.list}")
                        }
                    }
                }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main
}