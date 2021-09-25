package com.example.whatsappopener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var btnSearch : Button
    lateinit var etNum : EditText
    var number : String = "0"
    var check : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSearch = findViewById(R.id.btnSearch)
        etNum=findViewById(R.id.etNum)

        if (intent.action == Intent.ACTION_PROCESS_TEXT) {
            number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }

        btnSearch.setOnClickListener(object : View.OnClickListener {

            override fun onClick(num: View?) {
                var num = etNum.text.toString()
                if(num.isNotEmpty()){
                    check=true
                    startWhatsapp(num)
                }else{
                    Toast.makeText(baseContext, "Please check the number", Toast.LENGTH_SHORT).show()
                }
            }
        })

        if (number.isDigitsOnly()) {
            startWhatsapp(number)
        } else {
            Toast.makeText(this, "Please check the number", Toast.LENGTH_SHORT).show()
        }

    }

    private fun startWhatsapp(number: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        val data: String = if (number[0] == '+') {
            number.substring(1)
        } else if (number.length == 10) {
            "91" + number
        } else {
            number
        }
        intent.data = Uri.parse("https://wa.me/$data")
        if (packageManager.resolveActivity(intent, 0) != null && check==true) {
            startActivity(intent)
        }else if(packageManager.resolveActivity(intent, 0) != null && check==false) {
            //Do nothing
        }
        else {
            Toast.makeText(this, "Please install Whatsapp", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        finish()
        super.onPause()
    }

}