package com.example.qrpayment

import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.emv.qrcode.encoder.QRPayment

class QR_Code : AppCompatActivity() {
    private val QRPayment = QRPayment()
    lateinit var _txtresult:TextView
    lateinit var _btngenerate:Button
    lateinit var _imgview:ImageView
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q_r__code)
        context = this
        _txtresult = findViewById(R.id.txtqrresult)
        _btngenerate = findViewById(R.id.btngenerate)
        _imgview = findViewById(R.id.imgview)
        val intent = intent
        _btngenerate.setOnClickListener(View.OnClickListener {
            val emvcode = intent.getStringExtra("code")
            _txtresult.text = emvcode
            QRPayment.generateImage(_imgview,"",300,300)
        })
    }
}