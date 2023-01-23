package com.mlk.week3project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

var tasks = arrayListOf<TaskModel>()
//var users = arrayListOf<UserModel>()

class HomeActivity : AppCompatActivity() {

    val db = Firebase.firestore

    private lateinit var tasksRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



//        val reference = db.collection(COLLECTION_NAME)
//        val task = reference.get()
//
//        task.addOnSuccessListener {
//            Log.d("TAGGG","${it.documents[0].get("name")}")
//
//            for(doc in it){
//                val user = doc.toObject(UserModel::class.java)
//                Log.d("TAGGG","$user")
//                Log.d("TAGGG","${user.phone}")
//                Log.d("TAGGG","${user.email}")
//                Log.d("TAGGG","${user.name}")
//                users.add(user)
//            }
//
//        }
//        task.addOnFailureListener {
//            Log.d("TAGGG","${it.message}")
//        }
//
        val task1 = TaskModel("Go","Fast",false)
        tasks.add(task1)

        tasksRv = findViewById(R.id.HTasksRV)
        val addBU = findViewById<Button>(R.id.HAddBU)

        listAdapter()

        addBU.setOnClickListener{
            startActivity(Intent(this,AddTaskActivity::class.java))
        }
    }


    private fun listAdapter(){
        tasks.removeAll(tasks)
        db.collection("tasks")
            .get()
            .addOnSuccessListener {
//                for (doc in it){
//                    val task = doc.toObject<Task>()
//                    Log.d("TAGGG"," ${task}")
//                    tasks2.add(task)
//                }

                var i =1
                for (doc in it) {
                    Log.d("TAGGG","$i ${doc.data["title"]}")
                    val task = TaskModel(
                        doc.data["title"].toString(),
                        doc.data["note"].toString()
                    )
                    tasks.add(task)
                    i++
                }
                tasksRv.adapter = TasksRecyclerView(tasks)
            }
            .addOnFailureListener { exception ->
                Log.w("TAGGG", "Error getting documents.", exception)
            }

    }

    override fun onResume() {
        super.onResume()
        listAdapter()
    }
}