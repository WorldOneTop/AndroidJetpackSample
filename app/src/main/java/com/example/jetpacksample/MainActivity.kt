package com.example.jetpacksample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.local).setOnClickListener {
            startActivity(Intent(this@MainActivity, PagingActivity::class.java).putExtra("type",PagingActivity.TYPE_ROOM))
        }
        findViewById<Button>(R.id.network).setOnClickListener {
            startActivity(Intent(this@MainActivity, PagingActivity::class.java).putExtra("type",PagingActivity.TYPE_RETROFIT))
        }
    }
}