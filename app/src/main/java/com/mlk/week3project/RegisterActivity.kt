package com.mlk.week3project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val userModel = UserModel()
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val phoneET = findViewById<EditText>(R.id.RPhoneET)
        val emailET = findViewById<EditText>(R.id.REmailET)
        val nameET = findViewById<EditText>(R.id.RNameET)
        val registerBU = findViewById<Button>(R.id.RRegisterBU)

        toolbar.setNavigationOnClickListener {
            finish()
        }
        registerBU.setOnClickListener {
            // 0 = blank , -1 = wrong input , 1 = correct input
            val phoneCode = userModel.checkPhone(phoneET.text.toString())
            val emailCode = userModel.checkEmail(emailET.text.toString())
            val nameCode = userModel.checkName(nameET.text.toString())
            when{
                phoneCode == 1 && emailCode == 1 && nameCode == 1 ->{
                    userModel.addUser(
                        phoneET.text.toString(),
                        emailET.text.toString(),
                        nameET.text.toString(),
                        db
                    )
                    Toast.makeText(this,"User Added",Toast.LENGTH_SHORT).show()
                }

               phoneCode == 0 || emailCode == 0 || nameCode == 0 ->
                   Toast.makeText(this,"Empty fields!!",Toast.LENGTH_SHORT).show()
                phoneCode == -1 || emailCode == -1 || nameCode == -1 ->
                    Toast.makeText(this,"Wrong Entries!!",Toast.LENGTH_SHORT).show()
            }

        }
    }
}