package com.example.sos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val startTwoPlayerGameBtn = findViewById<Button>(R.id.startTwoPlayerGameBtn)


        startTwoPlayerGameBtn.setOnClickListener {
            val intent = Intent(this,TwoPlayer::class.java)
            startActivity(intent)
        }


    }
}