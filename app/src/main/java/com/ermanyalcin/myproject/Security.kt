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

class Security : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security)

        val sharedPreferences1 = this.getSharedPreferences("com.ermanyalcin.myproject",Context.MODE_PRIVATE)

        findViewById<TextView>(R.id.securityAnnouncement1).text = sharedPreferences1.getString("announcement1", "error")
        findViewById<TextView>(R.id.securityAnnouncement2).text = sharedPreferences1.getString("announcement2", "error")
        findViewById<TextView>(R.id.securityAnnouncement3).text = sharedPreferences1.getString("announcement3", "error")
        findViewById<TextView>(R.id.securityAnnouncement4).text = sharedPreferences1.getString("announcement4", "error")

        findViewById<TextView>(R.id.welcomeSecurity).text = "Welcome to the Resident Page as " + IdAndPasswordControll.takename.takeName

        updateAppoinmentGymSecurity()
        updateAppoinmentPoolSecurity()
    }

    fun goBackMainPage(view: View){
        startActivity(Intent(this,MainActivity::class.java))
    }

    @SuppressLint("SetTextI18n")
    fun updateAppoinmentGymSecurity(){
        if(Manager.saveAppointmentsGym.nameListGym.isNotEmpty()){
            findViewById<TextView>(R.id.ArrangeForGymSecurity).text = "Arrange for: " + Manager.saveAppointmentsGym.nameListGym[0]
        }
        else{
            findViewById<TextView>(R.id.ArrangeForGymSecurity).text = "Arrange for: -"
        }
    }

    @SuppressLint("SetTextI18n")
    fun updateAppoinmentPoolSecurity(){
        if(Manager.saveAppointmentsPool.nameListPool.isNotEmpty()){
            findViewById<TextView>(R.id.ArrangeForPoolSecurity).text = "Arrange for: " + Manager.saveAppointmentsPool.nameListPool[0]
        }
        else{
            findViewById<TextView>(R.id.ArrangeForPoolSecurity).text = "Arrange for: -"
        }
    }

    @SuppressLint("SetTextI18n")
    fun approveAppoinmentGymSecurity(view: View){
        if(Manager.saveAppointmentsGym.nameListGym.isNotEmpty()){
            val sharedPreferences1 = this.getSharedPreferences("com.ermanyalcin.myproject",Context.MODE_PRIVATE)
            sharedPreferences1.edit().putInt(Manager.saveAppointmentsGym.nameListGym.get(0)+"gym",2).apply()
            Manager.saveAppointmentsGym.nameListGym.removeAt(0)
            updateAppoinmentGymSecurity()
        }
        else{
            Toast.makeText(applicationContext, "There is no Appointment left", Toast.LENGTH_SHORT).show()
        }
    }

    fun DontApproveAppoinmentGymSecurity(view: View){
        if(Manager.saveAppointmentsGym.nameListGym.isNotEmpty()){
            val sharedPreferences1 = this.getSharedPreferences("com.ermanyalcin.myproject",Context.MODE_PRIVATE)
            sharedPreferences1.edit().putInt(Manager.saveAppointmentsGym.nameListGym.get(0)+"gym",3).apply()
            Manager.saveAppointmentsGym.nameListGym.removeAt(0)
            updateAppoinmentGymSecurity()
        }
        else{
            Toast.makeText(applicationContext, "There is no Appointment left", Toast.LENGTH_SHORT).show()
        }
    }

    fun approveAppoinmentPoolSecurity(view: View){
        if(Manager.saveAppointmentsPool.nameListPool.isNotEmpty()){
            val sharedPreferences1 = this.getSharedPreferences("com.ermanyalcin.myproject",Context.MODE_PRIVATE)
            sharedPreferences1.edit().putInt(Manager.saveAppointmentsPool.nameListPool.get(0)+"pool",2).apply()
            Manager.saveAppointmentsPool.nameListPool.removeAt(0)
            updateAppoinmentPoolSecurity()
        }
        else{
            Toast.makeText(applicationContext, "There is no Appointment left", Toast.LENGTH_SHORT).show()
        }
    }

    fun DontApproveAppoinmentPoolSecurity(view: View){
        if(Manager.saveAppointmentsPool.nameListPool.isNotEmpty()){
            val sharedPreferences1 = this.getSharedPreferences("com.ermanyalcin.myproject",Context.MODE_PRIVATE)
            sharedPreferences1.edit().putInt(Manager.saveAppointmentsPool.nameListPool.get(0)+"pool",3).apply()
            Manager.saveAppointmentsPool.nameListPool.removeAt(0)
            updateAppoinmentPoolSecurity()
        }
        else{
            Toast.makeText(applicationContext, "There is no Appointment left", Toast.LENGTH_SHORT).show()
        }
    }
}