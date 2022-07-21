package com.example.jetpacksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpacksample.databinding.ActivityMainBinding
import com.example.jetpacksample.room.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    private lateinit var userViewModel:UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        val userDao = AppDatabase.getInstance(applicationContext).userDao()
        userViewModel = ViewModelProvider(this, UserVMFactory(userDao))[UserViewModel::class.java]

        binding.viewModel = userViewModel
        binding.lifecycleOwner = this


        binding.btnAddRoom.setOnClickListener { btnOnClick(it) }
    }
    private fun btnOnClick(v: View){
        if(userViewModel.user.value == null){
            if(v.id == R.id.btnAddRoom)
                userViewModel.addNewUser()
        }else{
            Toast.makeText(this,"이미 데이터가 있습니다.",Toast.LENGTH_SHORT).show()
        }
    }

}