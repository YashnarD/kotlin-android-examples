package com.example.multipletable.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.multipletable.R
import com.example.multipletable.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.apply {
            customerBtn.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_customerFragment)
            }
            employeeBtn.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_employeeFragment)
            }
            orderBtn.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_orderFragment)
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}