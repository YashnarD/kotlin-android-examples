package com.example.chatappfirebaserealtimedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.chatappfirebaserealtimedatabase.adapters.AccountAdapter
import com.example.chatappfirebaserealtimedatabase.databinding.ActivityAccountBinding
import com.example.chatappfirebaserealtimedatabase.models.Account
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var list: ArrayList<Account>
    private lateinit var accountAdapter: AccountAdapter
    private val TAG = "AccountActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("users")

        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if(!it.isSuccessful) {
                Log.w(TAG, "onCreate: Fetching FCM registration token failed", it.exception)
                return@addOnCompleteListener
            }

            val token = it.result

            Log.d(TAG, "onCreate: $token")
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        }

        list = ArrayList()
        accountAdapter = AccountAdapter(list, object : AccountAdapter.OnItemClickListener {
            override fun onItemClick(account: Account) {
                val intent = Intent(this@AccountActivity, MessageActivity::class.java)
                intent.putExtra("account", account)
                startActivity(intent)
            }

        })

        binding.rv.adapter = accountAdapter

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                val children = snapshot.children
                for (child in children) {
                    val value = child.getValue(Account::class.java)
                    if (value != null && value.uid != firebaseAuth.uid) {
                        list.add(value)
                    }
                }
                accountAdapter.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}