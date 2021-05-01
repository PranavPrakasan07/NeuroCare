package com.example.neurocare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity2 : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        database = Firebase.database.reference

        database.child("pages").child("Help").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("firebase", "onDataChange ${snapshot.value.toString()}" )
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("firebase", "onCancelled ${error.message}" )

            }
        })
    }
}