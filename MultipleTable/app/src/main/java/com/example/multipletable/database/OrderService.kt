package com.example.multipletable.database

import com.example.multipletable.models.Customer
import com.example.multipletable.models.Employee
import com.example.multipletable.models.Order

interface OrderService {

    fun addCustomer(customer: Customer)

    fun addEmployee(employee: Employee)

    fun addOrder(order: Order)

    fun getCustomers(): List<Customer>

    fun getEmployees(): List<Employee>

    fun getOrders(): List<Order>

    fun getCustomerById(customerId: Int): Customer

    fun getEmployeeById(employeeId: Int): Employee
}