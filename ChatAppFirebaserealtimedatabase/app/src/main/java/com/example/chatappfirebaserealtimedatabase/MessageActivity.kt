package com.example.chatappfirebaserealtimedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatappfirebaserealtimedatabase.adapters.MessageAdapter
import com.example.chatappfirebaserealtimedatabase.databinding.ActivityMessageBinding
import com.example.chatappfirebaserealtimedatabase.models.Account
import com.example.chatappfirebaserealtimedatabase.models.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessageBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var list: ArrayList<Message>
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")

        val account = intent.getSerializableExtra("account") as Account

        list = ArrayList()
        messageAdapter = MessageAdapter(firebaseAuth.uid ?: "", list)
        binding.rvMessage.adapter = messageAdapter

        reference
            .child(firebaseAuth.uid?:"")
            .child("messages")
            .child(account.uid?:"")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    list.clear()
                    val children = snapshot.children
                    for (child in children) {
                        val value = child.getValue(Message::class.java)
                        if (value != null) {
                            list.add(value)
                        }
                    }
                    binding.rvMessage.scrollToPosition(list.size - 1)
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        binding.sendBtn.setOnClickListener {
            val messageText = binding.edit.text.toString()
            val message = Message(firebaseAuth.uid, account.uid, messageText, "")

            val key = reference.push().key

            reference.child(firebaseAuth.uid ?: "")
                .child("messages")
                .child(account.uid?:"")
                .child(key?:"")
                .setValue(message)

            reference.child(account.uid ?: "")
                .child("messages")
                .child(firebaseAuth.uid?:"")
                .child(key?:"")
                .setValue(message)

            binding.edit.setText("")
            binding.rvMessage.scrollToPosition(list.size - 1)
        }
    }
}