package com.ermanyalcin.myproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class Manager : AppCompatActivity() {

    object saveAppointmentsGym{
        val nameListGym = arrayListOf<String>()
    }
    object saveAppointmentsPool{
        val nameListPool = arrayListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager)

        val sharedPreferences1 = this.getSharedPreferences("com.ermanyalcin.myproject",Context.MODE_PRIVATE)

        findViewById<TextView>(R.id.managerAnnouncement1).text = sharedPreferences1.getString("announcement1", "Add Announcement..")
        findViewById<TextView>(R.id.managerAnnouncement2).text = sharedPreferences1.getString("announcement2", "Add Announcement..")
        findViewById<TextView>(R.id.managerAnnouncement3).text = sharedPreferences1.getString("announcement3", "Add Announcement..")
        findViewById<TextView>(R.id.managerAnnouncement4).text = sharedPreferences1.getString("announcement4", "Add Announcement..")

        updateAppoinmentGym()
        updateAppoinmentPool()
    }

    fun saveAnnouncements(view: View){

        val sharedPreferences1 = this.getSharedPreferences("com.ermanyalcin.myproject",Context.MODE_PRIVATE)

        sharedPreferences1.edit().putString("announcement1",findViewById<TextView>(R.id.managerAnnouncement1).text.toString()).apply()
        sharedPreferences1.edit().putString("announcement2",findViewById<TextView>(R.id.managerAnnouncement2).text.toString()).apply()
        sharedPreferences1.edit().putString("announcement3",findViewById<TextView>(R.id.managerAnnouncement3).text.toString()).apply()
        sharedPreferences1.edit().putString("announcement4",findViewById<TextView>(R.id.managerAnnouncement4).text.toString()).apply()

        Toast.makeText(applicationContext, "Announcements saved...", Toast.LENGTH_SHORT).show()
    }

    fun goBackMainPage(view: View){
        startActivity(Intent(this,MainActivity::class.java))
    }

    @SuppressLint("SetTextI18n")
    fun updateAppoinmentGym(){
        if(saveAppointmentsGym.nameListGym.isNotEmpty()){
            findViewById<TextView>(R.id.ArrangeForGymManager).text = "Arrange for: " + saveAppointmentsGym.nameListGym[0]
        }
        else{
            findViewById<TextView>(R.id.ArrangeForGymManager).text = "Arrange for: -"
        }
    }

    @SuppressLint("SetTextI18n")
    fun updateAppoinmentPool(){
        if(saveAppointmentsPool.nameListPool.isNotEmpty()){
            findViewById<TextView>(R.id.ArrangeForPoolManager).text = "Arrange for: " + saveAppointmentsPool.nameListPool[0]
        }
        else{
            findViewById<TextView>(R.id.ArrangeForPoolManager).text = "Arrange for: -"
        }
    }

    @SuppressLint("SetTextI18n")
    fun approveAppoinmentGym(view: View){
        if(saveAppointmentsGym.nameListGym.isNotEmpty()){
            val sharedPreferences1 = this.getSharedPreferences("com.ermanyalcin.myproject",Context.MODE_PRIVATE)
            sharedPreferences1.edit().putInt(saveAppointmentsGym.nameListGym.get(0)+"gym",2).apply()
            saveAppointmentsGym.nameListGym.removeAt(0)
            updateAppoinmentGym()
        }
        else{
            Toast.makeText(applicationContext, "There is no Appointment left", Toast.LENGTH_SHORT).show()
        }
    }

    fun DontApproveAppoinmentGym(view: View){
        if(saveAppointmentsGym.nameListGym.isNotEmpty()){
            val sharedPreferences1 = this.getSharedPreferences("com.ermanyalcin.myproject",Context.MODE_PRIVATE)
            sharedPreferences1.edit().putInt(saveAppointmentsGym.nameListGym.get(0)+"gym",3).apply()
            saveAppointmentsGym.nameListGym.removeAt(0)
            updateAppoinmentGym()
        }
        else{
            Toast.makeText(applicationContext, "There is no Appointment left", Toast.LENGTH_SHORT).show()
        }
    }

    fun approveAppoinmentPool(view: View){
        if(saveAppointmentsPool.nameListPool.isNotEmpty()){
            val sharedPreferences1 = this.getSharedPreferences("com.ermanyalcin.myproject",Context.MODE_PRIVATE)
            sharedPreferences1.edit().putInt(saveAppointmentsPool.nameListPool.get(0)+"pool",2).apply()
            saveAppointmentsPool.nameListPool.removeAt(0)
            updateAppoinmentPool()
        }
        else{
            Toast.makeText(applicationContext, "There is no Appointment left", Toast.LENGTH_SHORT).show()
        }
    }

    fun DontApproveAppoinmentPool(view: View){
        if(saveAppointmentsPool.nameListPool.isNotEmpty()){
            val sharedPreferences1 = this.getSharedPreferences("com.ermanyalcin.myproject",Context.MODE_PRIVATE)
            sharedPreferences1.edit().putInt(saveAppointmentsPool.nameListPool.get(0)+"pool",3).apply()
            saveAppointmentsPool.nameListPool.removeAt(0)
            updateAppoinmentPool()
        }
        else{
            Toast.makeText(applicationContext, "There is no Appointment left", Toast.LENGTH_SHORT).show()
        }
    }

}