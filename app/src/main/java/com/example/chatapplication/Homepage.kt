package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Homepage : AppCompatActivity() {

    private lateinit var myTextView: TextView
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var btn_profile: Button
    private lateinit var btn_chat: Button
    private lateinit var btn_work: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)



        //Variables
        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference()
        btn_chat = findViewById(R.id.btn_chat)
        btn_profile = findViewById(R.id.btn_profile)
        btn_work = findViewById(R.id.btn_work)




        //User profile Logo----------------------------------------------------------------
        val user = mAuth.currentUser
        val name = user?.displayName ?: ""
        val email = user?.email ?: ""
        val uid = user?.uid ?: ""


        //role separation






        //Name Logo

        mDbRef.child("user").child(uid).child("name").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val name = task.result?.value as? String
                val firstLetter = name?.capitalize() ?: ""
                myTextView = findViewById(R.id.profile)
                myTextView.text = firstLetter.toString()
            }
        }


        /*--------------------------------------------------------------------------------
        Buttons--------------------------------------------------*/


        btn_profile.setOnClickListener {
            val intent = Intent(this@Homepage, Profile::class.java)
            startActivity(intent)
        }


        btn_chat.setOnClickListener {
            val intent = Intent(this@Homepage, MainActivity::class.java)
            startActivity(intent)
        }

        btn_work.setOnClickListener {
            val intent = Intent(this@Homepage, Work::class.java)
            startActivity(intent)
        }

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.logout){

            mAuth.signOut()
            val intent = Intent(this@Homepage,LogIn::class.java)
            finish()
            startActivity(intent)
            return true
        }
        return true
    }
}