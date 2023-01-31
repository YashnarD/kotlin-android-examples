package com.example.multipletable.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.multipletable.R
import com.example.multipletable.database.MyDbHelper
import com.example.multipletable.databinding.FragmentOrderBinding
import com.example.multipletable.models.Customer
import com.example.multipletable.models.Employee
import com.example.multipletable.models.Order
import uz.mobiler.multipletablesg2122.adapters.CustomerAdapter
import uz.mobiler.multipletablesg2122.adapters.EmployeeAdapter
import uz.mobiler.multipletablesg2122.adapters.OrderAdapter

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private lateinit var myDbHelper: MyDbHelper
    private lateinit var list1: ArrayList<Customer>
    private lateinit var customerAdapter: CustomerAdapter

    private lateinit var list2: ArrayList<Employee>
    private lateinit var employeeAdapter: EmployeeAdapter

    private lateinit var list3: ArrayList<Order>
    private lateinit var orderAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)

        myDbHelper = MyDbHelper(requireContext())

        list1 = ArrayList(myDbHelper.getCustomers())
        customerAdapter = CustomerAdapter(list1)

        list2 = ArrayList(myDbHelper.getEmployees())
        employeeAdapter = EmployeeAdapter(list2)

        list3 = ArrayList(myDbHelper.getOrders())
        orderAdapter = OrderAdapter(list3)

        binding.apply {
            customerSpinner.adapter = customerAdapter
            employeeSpinner.adapter = employeeAdapter
            rv.adapter = orderAdapter

            saveBtn.setOnClickListener {
                val customer = list1[customerSpinner.selectedItemPosition]
                val employee = list2[employeeSpinner.selectedItemPosition]
                val orderDate = edit.text.toString()
                val order = Order(customer = customer, employee = employee, orderDate = orderDate)
                myDbHelper.addOrder(order)
                list3.add(order)
                orderAdapter.notifyItemInserted(list3.size)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}