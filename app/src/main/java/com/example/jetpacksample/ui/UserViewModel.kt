package com.example.jetpacksample.ui

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import com.example.jetpacksample.data.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date

class UserViewModel:ViewModel() {
    private val _userData:MutableLiveData<User> by lazy {
        MutableLiveData(User(1, "홍길동", "010-0000-0000"))
    }
    val userData: LiveData<User> get() = _userData

    val currentTime: LiveData<String> = liveData {
        while (true) {
            emit(Date(System.currentTimeMillis()).toString())
            delay(1000)
        }
    }
    val timeSound: LiveData<String> = currentTime.switchMap {
        liveData {
            emit("째깍")
            delay(300)
            emit("")
        }
    }



    fun updateUser(){
        _userData.value = User(1, "홍길동", "010-${getRandomNumber()}-${getRandomNumber()}")
    }

    private fun getRandomNumber() = (0..9999).random().toString().padStart(4, '0')
}
