package com.example.mvvmarchitecture.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mvvmarchitecture.R
import com.example.mvvmarchitecture.adapters.UserAdapter
import com.example.mvvmarchitecture.database.AppDatabase
import com.example.mvvmarchitecture.databinding.FragmentHomeBinding
import com.example.mvvmarchitecture.repository.UserRepository
import com.example.mvvmarchitecture.retrofit.ApiClient
import com.example.mvvmarchitecture.utils.*
import com.example.mvvmarchitecture.viewmodels.UserViewModel
import com.example.mvvmarchitecture.viewmodels.ViewModelFactory
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private lateinit var userViewModel: UserViewModel

    private lateinit var userAdapter: UserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(
            this, ViewModelFactory(
                UserRepository(
                    ApiClient.apiService
                ),
                NetworkHelper(requireContext())
            )
        )[UserViewModel::class.java]

        userAdapter = UserAdapter()
        binding.rv.adapter = userAdapter

        loadUi()

        binding.swipe.setOnRefreshListener {
            loadUi()
        }
    }

    fun loadUi() {
        lifecycleScope.launch {
            userViewModel.fetchUsers("sport")
                .collect {
                    when (it) {
                        is UserResource.Loading -> {
                            binding.swipe.isRefreshing = true
                        }
                        is UserResource.Error -> {
                            binding.swipe.isRefreshing = false
                            binding.tv.show()
                            requireContext().makeToast(it.message)
                        }
                        is UserResource.Success -> {
                            binding.swipe.isRefreshing = false
                            binding.tv.hide()
                            binding.rv.show()
                            userAdapter.submitList(it.headlines?.articles)
                        }
                    }
                }
        }
    }
}