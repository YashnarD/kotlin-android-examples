package com.example.dialogs

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.dialogs.adapters.ContactAdapter
import com.example.dialogs.databinding.ActivitySecondBinding
import com.example.dialogs.models.Contact
import com.google.android.material.snackbar.Snackbar


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var contactList: ArrayList<Contact>
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadContacts()

        contactAdapter = ContactAdapter(contactList, object : ContactAdapter.OnItemClickListener {
            override fun onItemClick(contact: Contact, position: Int, itemView: View) {
                contactList.remove(contact)
                contactAdapter.notifyItemRemoved(position)
                contactAdapter.notifyItemRangeChanged(position, contactList.size)
                val snackbar = Snackbar.make(itemView, "O'chirilmoqda", Snackbar.LENGTH_LONG)
                snackbar.setAction("Undo") { v: View? ->
                    contactList.add(position, contact!!)
                    contactAdapter.notifyItemInserted(position)
                    contactAdapter.notifyItemRangeChanged(position, contactList.size)
                }
                snackbar.show()
            }
        })
        binding.rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.rv.adapter = contactAdapter
    }

    private fun loadContacts() {
        contactList = ArrayList()

        for (i in 0..100) {
            contactList.add(Contact("Yashnar -> " + i, "+9989012345" + i))
        }
    }
}