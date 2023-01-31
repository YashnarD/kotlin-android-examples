package com.example.room

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.room.adapters.ContactAdapter
import com.example.room.database.AppDatabase
import com.example.room.databinding.ActivityMainBinding
import com.example.room.databinding.DialogBinding
import com.example.room.entities.Contact

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var list: ArrayList<Contact>
    private lateinit var contactAdapter: ContactAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)
        list = ArrayList(appDatabase.contactDao().getAllContacts())

        binding.apply {

            contactAdapter = ContactAdapter(list, object : ContactAdapter.OnItemClickListener{
                override fun onItemEdit(contact: Contact, position: Int) {

                    val dialog = AlertDialog.Builder(this@MainActivity)
                    val dialogBinding = DialogBinding.inflate(layoutInflater)
                    dialog.setView(dialogBinding.root)

                    dialogBinding.et1.setText(contact.name)
                    dialogBinding.et2.setText(contact.number)

                    dialog.setPositiveButton("Edit", object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            val newName = dialogBinding.et1.text.toString()
                            val newNumber = dialogBinding.et2.text.toString()
                            contact.name = newName
                            contact.number = newNumber

                            appDatabase.contactDao().editContact(contact)
                            contactAdapter.notifyItemChanged(position)
                        }

                    })

                    dialog.show()
                }

                override fun onItemDelete(contact: Contact, position: Int) {
                    appDatabase.contactDao().deleteContact(contact)
                    list.remove(contact)
                    contactAdapter.notifyItemRemoved(position)
                    contactAdapter.notifyItemRangeChanged(position, list.size)
                }

            })
            rv.adapter = contactAdapter

            saveBtn.setOnClickListener {
                val name = edit1.text.toString()
                val number = edit2.text.toString()

                val contact = Contact(name = name, number = number, age = 20)
                appDatabase.contactDao().addContact(contact)
                list.add(contact)
                contactAdapter.notifyItemInserted(list.size)
            }
        }

    }
}