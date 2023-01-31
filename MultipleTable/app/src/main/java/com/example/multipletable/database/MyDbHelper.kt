package com.example.multipletable.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.multipletable.models.Customer
import com.example.multipletable.models.Employee
import com.example.multipletable.models.Order

class MyDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), OrderService {

    companion object {
        const val DATABASE_NAME = "orders_db"
        const val DATABASE_VERSION = 1

        const val CUSTOMER = "customer"
        const val CUSTOMER_ID = "id"
        const val CUSTOMER_ADDRESS = "address"

        const val EMPLOYEE = "employee"
        const val EMPLOYEE_ID = "id"
        const val EMPLOYEE_NAME = "name"

        const val ORDERS = "orders"
        const val ORDER_ID = "id"
        const val ORDER_CUSTOMER_ID = "customer_id"
        const val ORDER_EMPLOYEE_ID = "employee_id"
        const val ORDER_DATE = "order_date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query1 =
            "create table $CUSTOMER($CUSTOMER_ID integer primary key autoincrement not null, $CUSTOMER_ADDRESS text not null)"
        val query2 =
            "create table $EMPLOYEE($EMPLOYEE_ID integer primary key autoincrement not null, $EMPLOYEE_NAME text not null)"
        val query3 =
            "create table $ORDERS($ORDER_ID integer primary key autoincrement not null, $ORDER_CUSTOMER_ID integer not null, $ORDER_EMPLOYEE_ID integer not null, $ORDER_DATE text not null, foreign key ($ORDER_CUSTOMER_ID) references $CUSTOMER($CUSTOMER_ID), foreign key ($ORDER_EMPLOYEE_ID) references $EMPLOYEE($EMPLOYEE_ID))"

        db?.execSQL(query1)
        db?.execSQL(query2)
        db?.execSQL(query3)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = "drop table $CUSTOMER"
        db?.execSQL(query)
    }

    override fun addCustomer(customer: Customer) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(CUSTOMER_ADDRESS, customer.address)
        database.insert(CUSTOMER, null, contentValues)
        database.close()
    }

    override fun addEmployee(employee: Employee) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(EMPLOYEE_NAME, employee.name)
        database.insert(EMPLOYEE, null, contentValues)
        database.close()
    }

    override fun addOrder(order: Order) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ORDER_CUSTOMER_ID, order.customer.id)
        contentValues.put(ORDER_EMPLOYEE_ID, order.employee.id)
        contentValues.put(ORDER_DATE, order.orderDate)
        database.insert(ORDERS, null, contentValues)
        database.close()
    }

    override fun getCustomers(): List<Customer> {

        val list = ArrayList<Customer>()

        val database = this.readableDatabase
        val query = "select * from $CUSTOMER"
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val address = cursor.getString(1)
                val customer = Customer(id, address)
                list.add(customer)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun getEmployees(): List<Employee> {
        val list = ArrayList<Employee>()

        val database = this.readableDatabase
        val query = "select * from $EMPLOYEE"
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val employee = Employee(id, name)
                list.add(employee)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun getOrders(): List<Order> {
        val list = ArrayList<Order>()

        val database = this.readableDatabase
        val query = "select * from $ORDERS"
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val customerId = cursor.getInt(1)
                val employeeId = cursor.getInt(2)
                val orderDate = cursor.getString(3)
                val customer = getCustomerById(customerId)
                val employee = getEmployeeById(employeeId)
                val order = Order(id, customer, employee, orderDate)
                list.add(order)
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun getCustomerById(customerId: Int): Customer {
        val database = this.readableDatabase

        val cursor = database.query(
            CUSTOMER,
            arrayOf(CUSTOMER_ID, CUSTOMER_ADDRESS),
            "$CUSTOMER_ID = ?",
            arrayOf(customerId.toString()),
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        return Customer(cursor.getInt(0), cursor.getString(1))
    }

    override fun getEmployeeById(employeeId: Int): Employee {
        val database = this.readableDatabase

        val cursor = database.query(
            EMPLOYEE,
            arrayOf(EMPLOYEE_ID, EMPLOYEE_NAME),
            "$EMPLOYEE_ID = ?",
            arrayOf(employeeId.toString()),
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        return Employee(cursor.getInt(0), cursor.getString(1))
    }
}