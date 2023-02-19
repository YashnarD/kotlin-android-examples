package com.example.livedataviewmodel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.livedataviewmodel.databinding.FragmentHomeBinding
import com.example.livedataviewmodel.models.User

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentHomeBinding? = null
    private lateinit var myViewModel: MyViewModel
    private val TAG = "HomeFragment"
    private lateinit var list: ArrayList<User>
    private lateinit var livedata: LiveData<List<User>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        livedata = myViewModel.getUsers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        _binding = binding
//        val users = UserService().getUsers()
//        Log.d(TAG, "onCreate: $users")

        livedata.observe(requireActivity(), Observer {
            Log.d(TAG, "onViewCreated: $it")
        })

        binding.button.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.firstFragment)
        }
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}