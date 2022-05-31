package com.ermanyalcin.myproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class Resident : AppCompatActivity() {

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resident)

        updateStatus()

        val sharedPreferences1 = this.getSharedPreferences("com.ermanyalcin.myproject",Context.MODE_PRIVATE)

        findViewById<TextView>(R.id.residentAnnouncement1).text = sharedPreferences1.getString("announcement1", "error")
        findViewById<TextView>(R.id.residentAnnouncement2).text = sharedPreferences1.getString("announcement2", "error")
        findViewById<TextView>(R.id.residentAnnouncement3).text = sharedPreferences1.getString("announcement3", "error")
        findViewById<TextView>(R.id.residentAnnouncement4).text = sharedPreferences1.getString("announcement4", "error")



        if(sharedPreferences1.getInt(IdAndPasswordControll.takename.takeName+"gym",0) == 0){
            sharedPreferences1.edit().putInt(IdAndPasswordControll.takename.takeName+"gym",1)
        }
        else{
            Toast.makeText(applicationContext, "You are already appointed for gym service", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    fun updateStatusPool(){

        val sharedPreferences1 = this.getSharedPreferences("com.ermanyalcin.myproject",Context.MODE_PRIVATE)
        val poolStatus = findViewById<TextView>(R.id.poolStatusResident)
        val appointmentStatusPool = sharedPreferences1.getInt(IdAndPasswordControll.takename.takeName+"pool",0)
        val btnTakeAppoinmentPool = findViewById<TextView>(R.id.takeAppointmentPool)

        if(appointmentStatusPool == 0){
            poolStatus.text = "Status: Doesn't have appointment"
        }
        else if(appointmentStatusPool == 1){
            poolStatus.text = "Status: Waiting for approve"
        }
        else if(appointmentStatusPool == 2){
            poolStatus.text = "Status: Your appointment is approved"
            btnTakeAppoinmentPool.text = "Cancel Appointment for pool"
        }
        else if(appointmentStatusPool == 3){
            poolStatus.text = "Status: Your appointment is don't approved"
            sharedPreferences1.edit().putInt(IdAndPasswordControll.takename.takeName+"pool", 0).apply()
            btnTakeAppoinmentPool.text = "Take Appointment for pool"
        }
        else{
            Toast.makeText(applicationContext, "Error!!!", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    fun takeAppointmentPool(view: View){
        val sharedPreferences1 = this.getSharedPreferences("com.ermanyalcin.myproject",Context.MODE_PRIVATE)
        val appointmentStatusPool = sharedPreferences1.getInt(IdAndPasswordControll.takename.takeName+"pool",0)
        val btnTakeAppoinmentPool = findViewById<TextView>(R.id.takeAppointmentPool)

        if(appointmentStatusPool == 0){
            sharedPreferences1.edit().putInt(IdAndPasswordControll.takename.takeName+"pool", 1).apply()
            updateStatusPool()
            btnTakeAppoinmentPool.text = "Cancel Appointment for pool"
            Manager.saveAppointmentsPool.nameListPool.add(IdAndPasswordControll.takename.takeName)
        }
        else{
            sharedPreferences1.edit().putInt(IdAndPasswordControll.takename.takeName+"pool", 0).apply()
            updateStatusPool()
            btnTakeAppoinmentPool.text = "Take Appointment for pool"
        }
    }

    @SuppressLint("SetTextI18n")
    fun updateStatusGym(){

        val sharedPreferences1 = this.getSharedPreferences("com.ermanyalcin.myproject",Context.MODE_PRIVATE)
        val gymStatus = findViewById<TextView>(R.id.gymStatusResident)
        val appointmentStatusGym = sharedPreferences1.getInt(IdAndPasswordControll.takename.takeName+"gym",0)
        val btnTakeAppoinmentGym = findViewById<TextView>(R.id.takeAppointmentGym)

        if(appointmentStatusGym == 0){
            gymStatus.text = "Status: Doesn't have appointment"
        }
        else if(appointmentStatusGym == 1){
            gymStatus.text = "Status: Waiting for approve"
        }
        else if(appointmentStatusGym == 2){
            gymStatus.text = "Status: Your appointment is approved"
            btnTakeAppoinmentGym.text = "Cancel Appointment for gym"
        }
        else if(appointmentStatusGym == 3){
            gymStatus.text = "Status: Your appointment is don't approved"
            sharedPreferences1.edit().putInt(IdAndPasswordControll.takename.takeName+"gym", 0).apply()
            btnTakeAppoinmentGym.text = "Take Appointment for gym"
        }
        else{
            Toast.makeText(applicationContext, "Error!!!", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    fun takeAppointmentGym(view: View){
        val sharedPreferences1 = this.getSharedPreferences("com.ermanyalcin.myproject",Context.MODE_PRIVATE)
        val appointmentStatusGym = sharedPreferences1.getInt(IdAndPasswordControll.takename.takeName+"gym",0)
        val btnTakeAppoinmentGym = findViewById<TextView>(R.id.takeAppointmentGym)

        if(appointmentStatusGym == 0){
            sharedPreferences1.edit().putInt(IdAndPasswordControll.takename.takeName+"gym", 1).apply()
            updateStatusGym()
            btnTakeAppoinmentGym.text = "Cancel Appointment for gym"
            Manager.saveAppointmentsGym.nameListGym.add(IdAndPasswordControll.takename.takeName)
        }
        else{
            sharedPreferences1.edit().putInt(IdAndPasswordControll.takename.takeName+"gym", 0).apply()
            updateStatusGym()
            btnTakeAppoinmentGym.text = "Take Appointment for gym"
        }
    }

    private fun updateStatus(){
        updateStatusGym()
        updateStatusPool()
    }

    fun goBackMainPage(view: View){
        startActivity(Intent(this,MainActivity::class.java))
    }
}