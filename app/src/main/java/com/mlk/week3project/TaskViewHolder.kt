package com.mlk.week3project

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class TaskViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val titleTV = view.findViewById<TextView>(R.id.ITitleTV)
    private val noteTV = view.findViewById<TextView>(R.id.INoteTV)
    private val statusIM = view.findViewById<ImageView>(R.id.IStatusIM)
    private val root = view.findViewById<ConstraintLayout>(R.id.root)

    fun bind(task: TaskModel){
        titleTV.text = task.title
        noteTV.text = task.note
        statusIM.setImageResource(task.statusInt)

        root.setOnLongClickListener {
            val intent = Intent(itemView.context,TaskActivity::class.java)
            intent.putExtra("title" , task.title)
            intent.putExtra("note" , task.note)
            intent.putExtra("status" , task.statusInt)
            itemView.context.startActivity(intent)
            false
        }

        root.setOnClickListener {
            task.statusInt =task.changeStatusImage()
            statusIM.setImageResource(task.statusInt)
        }
    }

}