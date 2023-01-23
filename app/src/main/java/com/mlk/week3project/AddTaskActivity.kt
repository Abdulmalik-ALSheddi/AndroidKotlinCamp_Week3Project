package com.mlk.week3project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddTaskActivity : AppCompatActivity() {

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val toolbar = findViewById<Toolbar>(R.id.AToolbar)
        val titleET = findViewById<EditText>(R.id.ATitleET)
        val noteET = findViewById<EditText>(R.id.ANoteET)
        val addBU = findViewById<Button>(R.id.AAddBU)

        toolbar.setNavigationOnClickListener{
            finish()
        }

        addBU.setOnClickListener{
            val task1 = TaskModel(titleET.text.toString(),noteET.text.toString())
            if (task1.checkTitle(titleET.text) && task1.checkNote(noteET.text)){

                // Create a new task with a first and last name
                val task = hashMapOf(
                    "title" to titleET.text.toString(),
                    "note" to noteET.text.toString(),
                    "status" to false
                )

                // Add a new document with a generated ID
                db.collection("tasks")
                    .add(task)
                    .addOnSuccessListener { documentReference ->
                        Log.d("TAGGG", "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w("TAGGG", "Error adding document", e)
                    }
//                tasks.add(task1)
                Toast.makeText(this,"Task Added!",Toast.LENGTH_SHORT).show()
                finish()
            }else
                Toast.makeText(this,"Title and Note can't be empty",Toast.LENGTH_SHORT).show()

        }
    }
}