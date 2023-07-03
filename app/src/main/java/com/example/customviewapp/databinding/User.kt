package com.example.customviewapp.databinding

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.BaseObservable

class User(var firstName: String, var lastName: String): BaseObservable()

class ClickHandler {
    fun buttonClick(view: View) {
        Log.i("ClickHandler", "buttonClick")
        Toast.makeText(view.context, "buttonClick", Toast.LENGTH_SHORT).show()
    }

    fun onUserInfoClick(user: User) {
        user.firstName = "hello"
        user.lastName = "world"
        Log.i("ClickHandler", "firstName:" + user.firstName + ",lastName:" + user.lastName)
    }
}