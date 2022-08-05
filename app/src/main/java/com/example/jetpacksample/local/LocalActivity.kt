package com.example.jetpacksample.local

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpacksample.MyVMFactory
import com.example.jetpacksample.MyViewModel
import com.example.jetpacksample.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LocalActivity : AppCompatActivity() {
    private lateinit var viewModel:MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local)

        val adapter = LocalAdapter()
        findViewById<RecyclerView>(R.id.dataList).adapter = adapter

        viewModel = ViewModelProvider(this, MyVMFactory(application))[MyViewModel::class.java]


        lifecycleScope.launch {
            viewModel.data.collectLatest { adapter.submitData(it) }
        }

        initSwipeToDelete()
        initAddButtonListener()
    }


    private fun initSwipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val cheeseViewHolder = viewHolder as LocalViewHolder
                return if (cheeseViewHolder.word != null) {
                    makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
                } else {
                    makeMovementFlags(0, 0)
                }
            }

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                (viewHolder as LocalViewHolder).word?.let {
                    viewModel.remove(it)
                }
            }
        }).attachToRecyclerView(findViewById(R.id.dataList))
    }

    private fun initAddButtonListener() {
        findViewById<Button>(R.id.addButton).setOnClickListener {
            val newData = findViewById<EditText>(R.id.inputText).text.trim()
            if (newData.isNotEmpty()) {
                viewModel.insert(newData)
                findViewById<EditText>(R.id.inputText).setText("")
            }
        }
    }
}