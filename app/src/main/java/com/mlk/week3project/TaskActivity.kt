package com.mlk.week3project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class TaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val statusIM =findViewById<ImageView>(R.id.TStatusIM)
        val titleTV = findViewById<TextView>(R.id.TTitleTV)
        val noteTV = findViewById<TextView>(R.id.TNoteTV)
        val titleET = findViewById<EditText>(R.id.TTitleET)
        val noteET = findViewById<EditText>(R.id.TNoteET)
        val editBU = findViewById<Button>(R.id.TEditBU)
        val deleteBU = findViewById<Button>(R.id.TDeleteBU)

        statusIM.setImageResource(intent.getIntExtra("status",-1))
        titleTV.text = intent.getStringExtra("title")
        noteTV.text = intent.getStringExtra("note")

        deleteBU.setOnClickListener{
            val snac = Snackbar.make(it,"Are you sure ?",Snackbar.LENGTH_LONG)
            snac.setAction("Yes"){
                val task = TaskModel(titleTV.text.toString(),noteTV.text.toString())
                if (task.removeByTitle(titleTV.text)){
                    Toast.makeText(this,"Task Removed!",Toast.LENGTH_SHORT).show()
                    finish()
                }
                else
                    Toast.makeText(this,"Task Not Found!!",Toast.LENGTH_SHORT).show()
            }.show()
        }

        editBU.setOnClickListener {
            val task = TaskModel(titleET.text.toString(),noteET.text.toString())
            if (task.checkTitle(titleET.text) && task.checkNote(noteET.text)){
                val tempTask = task.editByTitle(titleTV.text,titleET.text,noteET.text)
                if (tempTask != null)
                {
                    titleTV.text = tempTask.title
                    noteTV.text = tempTask.note
                    Toast.makeText(this,"Task Updated!",Toast.LENGTH_SHORT).show()
                }else
                    Toast.makeText(this,"Task Not Found!!",Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(this,"Enter the new Title & Note",Toast.LENGTH_SHORT).show()
        }
    }
}