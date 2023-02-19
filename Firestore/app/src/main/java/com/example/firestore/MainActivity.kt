package com.example.firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.firestore.databinding.ActivityMainBinding
import com.example.firestore.models.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var firestore: FirebaseFirestore

    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var storageReference: StorageReference
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()


        firebaseStorage = FirebaseStorage.getInstance()

        binding.button.setOnClickListener {
            val m = System.currentTimeMillis()
            storageReference = firebaseStorage.getReference("$m")
            getContent.launch("image/*")

//            val user = User("Yashnar", 20)
//            firestore.collection("users")
//                .add(user)
//                .addOnSuccessListener {
//                    Toast.makeText(this, "${it.id}", Toast.LENGTH_SHORT).show()
//                }.addOnFailureListener {
//                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
//                }

//            firestore.collection("users")
//                .get().addOnSuccessListener { result ->
//                    for (queryDocumentSnapshot in result) {
//                        val user = queryDocumentSnapshot.toObject(User::class.java)
//                        Log.d("SSSS", "onCreate: ${user}")
//                    }
//                }.addOnFailureListener {
//                    Log.d("SSSS", "onCreate: ${it.message}")
//                }
        }
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if (it != null) {
            storageReference.putFile(it)
                .addOnSuccessListener {
                    if (it?.task?.isSuccessful == true) {
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                        val downloadUrl = it.metadata?.reference?.downloadUrl
                        downloadUrl?.addOnSuccessListener { uri ->
                            imageUrl = uri.toString()

                            val user = User(imageUrl, 20)
                            firestore.collection("users")
                                .add(user)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "${it.id}", Toast.LENGTH_SHORT).show()
                                }.addOnFailureListener {
                                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                }
        }
    }
}