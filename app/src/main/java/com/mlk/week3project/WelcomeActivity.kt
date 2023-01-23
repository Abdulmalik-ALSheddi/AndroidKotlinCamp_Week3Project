package com.mlk.week3project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val loginBU = findViewById<Button>(R.id.WLoginBU)
        val registerBU = findViewById<Button>(R.id.WRegisterBU)

        loginBU.setOnClickListener {
            startActivity(Intent(applicationContext,LoginActivity::class.java))
        }
        registerBU.setOnClickListener {
            startActivity(Intent(applicationContext,RegisterActivity::class.java))
        }
    }
}