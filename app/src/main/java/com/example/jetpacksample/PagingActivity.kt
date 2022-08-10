package com.example.jetpacksample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpacksample.databinding.ActivityPagingBinding
import com.example.jetpacksample.RoomDB
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PagingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPagingBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: UserViewModel

    companion object{
        const val TYPE_ROOM = 0
        const val TYPE_RETROFIT = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        binding.dataList.adapter = adapter

        viewModel = ViewModelProvider(this, UserVMFactory(
            intent.getIntExtra("type",TYPE_ROOM), RoomDB.getInstance(applicationContext).userDao()
        ))[UserViewModel::class.java]

        lifecycleScope.launch {
            viewModel.data.collectLatest { adapter.submitData(it) }
        }
    }
}