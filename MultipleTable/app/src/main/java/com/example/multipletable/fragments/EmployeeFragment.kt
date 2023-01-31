package com.example.multipletable.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.multipletable.R
import com.example.multipletable.database.MyDbHelper
import com.example.multipletable.databinding.FragmentEmployeeBinding
import com.example.multipletable.models.Employee

class EmployeeFragment : Fragment() {

    private var _binding: FragmentEmployeeBinding? = null
    private val binding get() = _binding!!
    private lateinit var myDbHelper: MyDbHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeBinding.inflate(inflater, container, false)

        myDbHelper = MyDbHelper(requireContext())

        binding.apply {
            saveBtn.setOnClickListener {
                val name = edit.text.toString()
                val employee = Employee(name = name)
                myDbHelper.addEmployee(employee)
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