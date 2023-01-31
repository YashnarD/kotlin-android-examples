package com.example.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragment.databinding.FragmentHomeBinding
import com.example.fragments.models.User

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            val s1 = binding.edit1.text.toString()
            val s2 = binding.edit2.text.toString()
            val user = User(s1, s2)

            val firstFragment = FirstFragment.newInstance(user)
            val parentFragmentManager = parentFragmentManager
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.root_layout, firstFragment, "first")
            fragmentTransaction.addToBackStack(firstFragment.toString())
            fragmentTransaction.commit()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}