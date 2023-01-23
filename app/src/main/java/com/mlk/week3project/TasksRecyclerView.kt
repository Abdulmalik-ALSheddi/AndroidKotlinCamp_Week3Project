package com.mlk.week3project

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class TasksRecyclerView(
    private val tasks: ArrayList<TaskModel>
): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TaskViewHolder(inflater.inflate(R.layout.task_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("TAGGG","!!!!!!!!!!!!!${tasks.size}")
        if (holder is TaskViewHolder)
            holder.bind(tasks[position])
    }

    override fun getItemCount() = tasks.size
}