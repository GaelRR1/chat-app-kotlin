package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Profile : AppCompatActivity() {

    private lateinit var myTextView: TextView
    private lateinit var myTextView1: TextView
    private lateinit var myTextView2: TextView
    private lateinit var myTextView3: TextView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var addProfile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference()
        addProfile = findViewById(R.id.addProfile)

        val user = mAuth.currentUser
        val name = user?.displayName ?: "Unknown" // Set default value if name is null
        val email = user?.email ?: ""
        val uid = user?.uid ?: ""

        mDbRef.child("users").child(uid).child("role").get().addOnSuccessListener { role ->
            val rolle = role.value.toString() // Convert role to string
            myTextView3 = findViewById(R.id.role_profile)
            myTextView3.text = "Role: You are $rolle."
        }.addOnFailureListener {
            // Handle error
        }

        //Change name on logo
        myTextView = findViewById(R.id.profile5)
        myTextView.text = name

        //Change name to actual
        myTextView = findViewById(R.id.name_profile)
        myTextView.text = "Name: Hello $name"

        //Change email to actual
        myTextView1 = findViewById(R.id.email_profile)
        myTextView1.text = "Email: Your email is \"$email\"."

        //Change uid to actual
        myTextView2 = findViewById(R.id.uid_profile)
        myTextView2.text = "Uid: Your uid is \"$uid\"."

        //Button role
    }
}