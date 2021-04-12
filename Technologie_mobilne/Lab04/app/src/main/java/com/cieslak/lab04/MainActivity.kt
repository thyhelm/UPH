package com.cieslak.lab04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, StudentsListActivity::class.java)
        var startButton = findViewById<Button>(R.id.start)

        startButton.setOnClickListener {
            startActivity(intent)
        }
    }
}