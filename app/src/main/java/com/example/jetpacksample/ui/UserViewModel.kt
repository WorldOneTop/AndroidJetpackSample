package com.example.jetpacksample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpacksample.data.User

class UserViewModel:ViewModel() {
    private var user: User = User(1, "홍길동", "010-0000-0000")

    fun getUser() = user

    fun updateUser(){
        user.phoneNumber = "010-${getRandomNumber()}-${getRandomNumber()}"
    }

    private fun getRandomNumber() = (0..9999).random().toString().padStart(4, '0')
}
