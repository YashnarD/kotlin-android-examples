package com.example.recyclerview1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview1.adapters.ContactAdapter
import com.example.recyclerview1.databinding.ActivityMainBinding
import com.example.recyclerview1.models.Contact


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactList: ArrayList<Contact>
    private lateinit var contactAdapter: ContactAdapter
    private var clickPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadContacts()

        contactAdapter =
            ContactAdapter(contactList, object : ContactAdapter.OnItemContactClickListener {
                override fun onItemContactClick(contact: Contact) {
                    val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@MainActivity, contact.name, Toast.LENGTH_SHORT).show()
                }

                override fun onItemDeleteClick(contact: Contact, position: Int) {
                    contactList.remove(contact)
                    contactAdapter.notifyItemRemoved(position)
                    contactAdapter.notifyItemRangeChanged(position, contactList.size)
                }

                override fun onItemEditClick(contact: Contact, position: Int) {
                    clickPosition = position
                    binding.nameEt.setText(contact.name)
                    binding.numberEt.setText(contact.number)
                }
            })
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rv.layoutManager = linearLayoutManager
        binding.rv.adapter = contactAdapter

        binding.saveBtn.setOnClickListener { v ->
            if (clickPosition != -1) {
                val name = binding.nameEt.text.toString()
                val number = binding.numberEt.text.toString()
                contactList[clickPosition].name = name
                contactList[clickPosition].number = number
                contactAdapter.notifyItemChanged(clickPosition)
                clickPosition = -1
            } else {
                val name = binding.nameEt.text.toString()
                val number = binding.numberEt.text.toString()
                val contact = Contact(name, number)
                contactList.add(contact)
                contactAdapter.notifyItemInserted(contactList.size)
            }
            binding.nameEt.setText("")
            binding.numberEt.setText("")
        }
    }

    private fun loadContacts() {
        contactList = ArrayList()

        for (i in 0 until 100) {
            if (i % 3 == 0) {
                contactList.add(
                    Contact(
                        "Yashnarbek Melikov android java kotlin pdp asdwer fe" + i,
                        "+998906772149" + i
                    )
                )
            } else {
                contactList.add(Contact("Yashnarbek " + i, "+998906772149" + i))
            }
        }
    }
}