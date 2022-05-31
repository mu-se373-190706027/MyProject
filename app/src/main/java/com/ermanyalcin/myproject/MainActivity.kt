package com.ermanyalcin.myproject

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    object takenFromMainActivity {
        var nameSelection = ""
        var IdSelection = "-1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun residentSelection(view: View){
        takenFromMainActivity.nameSelection = "Resident"
        takenFromMainActivity.IdSelection = "1"
        startActivity(Intent(this,IdAndPasswordControll::class.java))
    }
    fun securitySelection(view: View){
        takenFromMainActivity.nameSelection = "Security"
        takenFromMainActivity.IdSelection = "2"
        startActivity(Intent(this,IdAndPasswordControll::class.java))
    }
    fun managerSelection(view: View){
        takenFromMainActivity.nameSelection = "Manager"
        takenFromMainActivity.IdSelection = "3"
        startActivity(Intent(this,IdAndPasswordControll::class.java))
    }
    fun openAdminEnterance(view: View){
        startActivity(Intent(this,AdminEnterance::class.java))
    }
}