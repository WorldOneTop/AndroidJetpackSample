package com.example.jetpacksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.jetpacksample.data.User
import com.example.jetpacksample.databinding.ActivityMainBinding
import com.example.jetpacksample.ui.UserViewModel

    class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: UserViewModel by viewModels()
    val title: String = "JETPACK"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myData = this

        binding.viewModel = viewModel

        viewModel.userData.observe(this){ newUser ->
            binding.text2.text = newUser.toString()
        }
        binding.lifecycleOwner = this
    }
    fun onClickName(view: View){
        Toast.makeText(this, getString(R.string.app_name), Toast.LENGTH_SHORT).show()
    }

    fun onClickUser(user: User){
        Toast.makeText(this, user.name, Toast.LENGTH_SHORT).show()
    }



}