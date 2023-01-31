package com.example.sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqlite.adapters.ContactAdapter
import com.example.sqlite.database.MyDbHelper
import com.example.sqlite.databinding.ActivityMainBinding
import com.example.sqlite.models.Contact

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var list: ArrayList<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)
        list = ArrayList(myDbHelper.getAllContacts())

        contactAdapter = ContactAdapter(list, object : ContactAdapter.OnItemClickListener {
            override fun onItemDelete(contact: Contact, position: Int) {
                myDbHelper.deleteContact(contact)
                list.removeAt(position)
                contactAdapter.notifyItemRemoved(position)
                contactAdapter.notifyItemRangeChanged(position, list.size)
            }

            override fun onItemEdit(contact: Contact, position: Int) {
                contact.name = "PDP"
                contact.number = "7777777"
                myDbHelper.editContact(contact)
                contactAdapter.notifyItemChanged(position)
            }

        })

        binding.apply {
            saveBtn.setOnClickListener {
                val name = edit1.text.toString()
                val number = edit2.text.toString()
                val contact = Contact(name = name, number = number)
                myDbHelper.addContact(contact)
                list.add(contact)
                contactAdapter.notifyItemInserted(list.size)
            }
            rv.adapter = contactAdapter
        }

    }
}
