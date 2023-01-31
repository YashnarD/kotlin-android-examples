package com.example.navigation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.navigation.databinding.FragmentHomeBinding
import com.example.navigation.utils.MyNavOptions

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

//        val intent = Intent(requireContext(), MainActivity::class.java)
//        requireContext().startActivity(intent)
        binding.button.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("key", "Yashnar")
            Navigation.findNavController(it).navigate(R.id.firstFragment, bundle, MyNavOptions.getNavOptions())
        }

        binding.button1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.secondFragment, null, MyNavOptions.getNavOptions())
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}