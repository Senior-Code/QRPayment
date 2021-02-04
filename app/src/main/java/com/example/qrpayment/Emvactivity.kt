package com.example.qrpayment



import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.emv.qrpayment.encoder.QRPayment
import java.io.InputStream


class Emvactivity : AppCompatActivity() {
    private lateinit var _guidspn: Spinner
    private lateinit var _merchantidedt: EditText
    private lateinit var _merchantcategorycodeedt: EditText
    private lateinit var _totalamountedt: EditText
    private lateinit var _currencyspn: Spinner
    private lateinit var _merchantnameedt: EditText
    private lateinit var _identitycodeedt: EditText
    private lateinit var _customernoedt: EditText
    private lateinit var _merchantcity: EditText
    private lateinit var _generatorbtn: Button
    private lateinit var _txtresult: TextView
    private lateinit var _imgview: ImageView
    private val QRPayment = QRPayment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emvactivity)
        _guidspn = findViewById(R.id.guid_spn)
        _merchantidedt = findViewById(R.id.merchantid_edt)
        _merchantcategorycodeedt = findViewById(R.id.categorycode_edt)
        _totalamountedt = findViewById(R.id.totalamount_edt)
        _currencyspn = findViewById(R.id.currency_spn)
        _merchantnameedt = findViewById(R.id.merchantname_edt)
        _identitycodeedt = findViewById(R.id.identitycode_edt)
        _customernoedt = findViewById(R.id.customernumber_edt)
        _merchantcity = findViewById(R.id.merchantcity_edt)
        _generatorbtn = findViewById(R.id.generator_btn)
        _imgview = findViewById(R.id.imgview)
        _txtresult = findViewById(R.id.txtqrresult)
        _generatorbtn.setOnClickListener()
        {
            val inputStream: InputStream = resources.openRawResource(R.raw.configfee)
            QRPayment.setFilePath(inputStream)
            var GUID = ""
            val ACLEDA = "kh.com.acledabank.www"
            val AMK = "amkbkhppxxx@amkb"
            when (_guidspn.selectedItemPosition) {
                1 -> {
                    GUID = ACLEDA
                }
                0 -> {
                    GUID = AMK
                }
            }
            var transactioncurrency = ""
            when (_currencyspn.selectedItemPosition) {
                0 -> {
                    transactioncurrency = "116" //KHR
                }
                1 -> {
                    transactioncurrency = "840" //USD
                }
                2 -> {
                    transactioncurrency = "764" //THB
                }
            }
            var merchantCategoryCode: String
            if (_merchantcategorycodeedt.text.isEmpty()){
                merchantCategoryCode = "4900"
            } else {
                merchantCategoryCode = _merchantcategorycodeedt.text.toString()
            }
            QRPayment.setPayment(
                GUID,
                _merchantidedt.text.toString(),
                merchantCategoryCode,
                transactioncurrency,
                _totalamountedt.text.toString(),
                _identitycodeedt.text.toString(),
                _customernoedt.text.toString(),
                _merchantnameedt.text.toString(),
                _merchantcity.text.toString()
            )
            var emvcode = QRPayment.generateText()
            _txtresult.text = emvcode
            QRPayment.generateImage(_imgview, emvcode, 300, 300)
        }
    }
}
