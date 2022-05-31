package com.ermanyalcin.myproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class AdminEnterance : AppCompatActivity() {

    private var password = "753951"
    private var id = "admin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_enterance)
    }
    fun CheckPassword(view:View){
        val enteredId = findViewById<TextView>(R.id.editTextAdmin).text.toString()
        val enteredPassword = findViewById<TextView>(R.id.editTextAdminPassword).text.toString()
        if(enteredId == id && enteredPassword == password){
            startActivity(Intent(this,RegisterActivity::class.java))
        }
        else{
            Toast.makeText(this,"Wrong password or id please try again!!!" , Toast.LENGTH_SHORT).show()
        }
    }
    fun openMainPage(view: View){
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }
}