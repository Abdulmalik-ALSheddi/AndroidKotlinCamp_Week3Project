package com.mlk.week3project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userModel = UserModel()
        val toolBar = findViewById<Toolbar>(R.id.LToolBar)
        val phoneET = findViewById<EditText>(R.id.LPhoneET)
        val loginBU = findViewById<Button>(R.id.LLoginBU)

        toolBar.setNavigationOnClickListener {
            finish()
        }

        loginBU.setOnClickListener {
            val phone = phoneET.text.toString()
            db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    for (doc in result) {
                        if (doc.data["phone"] == phone){
                            startActivity(Intent(applicationContext,HomeActivity::class.java))
                            Log.d("TAGGG", "FOUND!!")
                        }
//                    Log.d("TAGGG", "${doc.id} => ${doc.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("TAGGG", "Error getting documents.", exception)
                }
        }
    }
}