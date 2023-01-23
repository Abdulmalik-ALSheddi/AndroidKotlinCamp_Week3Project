package com.mlk.week3project

import android.text.Editable
import android.widget.EditText


class TaskModel( var title: String , var note: String , var status: Boolean = false){

    fun checkTitle(title: Editable) = title.toString().isNotBlank()
    fun checkNote(note: Editable) = note.toString().isNotBlank()

    var statusInt = R.drawable.ic_pending
    init {
        if (status)
            statusInt = R.drawable.ic_done
    }
    fun changeStatusImage(): Int {
        this.status = !status
        return if (status)
            R.drawable.ic_done
        else
            R.drawable.ic_pending
    }

    fun removeByTitle(title: CharSequence): Boolean {
        for (i in tasks){
            if (i.title == title){
                tasks.remove(i)
                return true
            }
        }
        return false
    }

    fun editByTitle(oldTitle: CharSequence, newTitle: CharSequence, newNote: CharSequence): TaskModel? {
        for (i in tasks){
            if (i.title == oldTitle){
                i.title = newTitle.toString()
                i.note = newNote.toString()
                return i
            }
        }
        return null
    }
}