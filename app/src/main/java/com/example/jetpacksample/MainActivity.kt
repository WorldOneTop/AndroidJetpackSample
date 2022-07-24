package com.example.jetpacksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.example.jetpacksample.databinding.ActivityMainBinding
import com.example.jetpacksample.retrofit.ResponseData
import com.example.jetpacksample.retrofit.RetrofitAPI
import com.example.jetpacksample.room.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    private lateinit var userViewModel:UserViewModel
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        userDao = AppDatabase.getInstance(applicationContext).userDao()
        userViewModel = ViewModelProvider(this, UserVMFactory(userDao))[UserViewModel::class.java]

        binding.viewModel = userViewModel
        binding.lifecycleOwner = this


        binding.btnAddRoom.setOnClickListener { btnOnClick(it) }
        binding.btnAddRetrofit.setOnClickListener { btnOnClick(it) }
    }
    private fun btnOnClick(v: View){
        if(userViewModel.user.value == null){
            if(v.id == R.id.btnAddRoom)
                userViewModel.addNewUser()
            else{
                getUserNetwork(userViewModel.currentID.value ?: 0)
            }
        }else{
            Toast.makeText(this,"이미 데이터가 있습니다.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun getUserNetwork(id:Int){
        RetrofitAPI.request.getUser(id)
            .enqueue(object : retrofit2.Callback<ResponseData> {
                override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                    if(response.code() == 404){
                        Toast.makeText(this@MainActivity,"해당 아이디의 유저가 없습니다.",Toast.LENGTH_SHORT).show()
                    }else if(response.code() == 200){
                        CoroutineScope(Dispatchers.IO).launch {
                            userDao.insertAll(response.body()!!.user!!)
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                        Toast.makeText(this@MainActivity,"서버와 연결이 실패하였습니다.",Toast.LENGTH_SHORT).show()
                }
            })
    }

}

