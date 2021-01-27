package com.example.qrpayment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var _btn1:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        _btn1 = findViewById(R.id.btn1)
        val intent = Intent(this, Emvactivity::class.java)
        _btn1.setOnClickListener {
            startActivity(intent)
        }

    }

}
