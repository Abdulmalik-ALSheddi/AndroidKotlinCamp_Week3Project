package com.mlk.week3project

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class UserModel(val email: String = "null" , val name: String= "null", val phone: String= "null") {
    private val phoneNum = "0551916269"


//    fun findPhone(phone: String, db: FirebaseFirestore): Boolean {
//        var found = false
//        db.collection("users")
//            .get()
//            .addOnSuccessListener { result ->
//                for (doc in result) {
//                    if (doc.data["phone"] == phone){
//                        found = true
//                        Log.d("TAGGG", "FOUND!!")
//                    }
////                    Log.d("TAGGG", "${doc.id} => ${doc.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w("TAGGG", "Error getting documents.", exception)
//            }
//        return found
//    }


    fun checkPhone(phone: String): Int {
        return when{
            phone.isBlank() -> 0
            (phone.length != 13 && phone.length != 10) -> -1
            !(phone.startsWith("05") || phone.startsWith("+966")) -> -1
            else -> 1
        }
    }
    fun checkEmail(email: String): Int{
        email.trim()
        return when{
            email.isBlank() -> 0
            email.startsWith("@") -> -1
            !email.endsWith(".com") -> -1
            !email.contains("@gmail",true) -> -1
            else -> 1
        }
    }

    fun checkName(name: String): Int {
        return when{
            name.isBlank() -> 0
            name.length < 3 -> -1
            else -> 1
        }
    }

    fun addUser(phone: String, email: String, name: String, db: FirebaseFirestore) {
        val user = hashMapOf(
            "phone" to phone,
            "email" to email,
            "name" to name
        )

// Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("TAGGG", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TAGGG", "Error adding document", e)
            }
    }


}