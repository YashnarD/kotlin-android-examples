package com.example.multipletable.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.multipletable.R
import com.example.multipletable.database.MyDbHelper
import com.example.multipletable.databinding.FragmentCustomerBinding
import com.example.multipletable.models.Customer

class CustomerFragment : Fragment() {

    private var _binding: FragmentCustomerBinding? = null

    private val binding get() = _binding!!
    private lateinit var myDbHelper: MyDbHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomerBinding.inflate(inflater, container, false)

        myDbHelper = MyDbHelper(requireContext())

        binding.apply {
            saveBtn.setOnClickListener {
                val address = edit.text.toString()
                val customer = Customer(address = address)
                myDbHelper.addCustomer(customer)
                findNavController().popBackStack()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}