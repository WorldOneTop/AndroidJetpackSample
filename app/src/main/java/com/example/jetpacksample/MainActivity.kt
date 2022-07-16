package com.example.jetpacksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.jetpacksample.data.User
import com.example.jetpacksample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val title: String = "JETPACK"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myData = this
        binding.user = User(1, "홍길동", "010-0000-0000")

    }

    fun onClickName(view: View){
        Toast.makeText(this, getString(R.string.app_name), Toast.LENGTH_SHORT).show()
    }

    fun onClickUser(user: User){
        Toast.makeText(this, user.name, Toast.LENGTH_SHORT).show()
    }

}