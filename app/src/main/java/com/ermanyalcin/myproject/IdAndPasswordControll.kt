package com.ermanyalcin.myproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap

class IdAndPasswordControll : AppCompatActivity() {

    object takename{
        var takeName = ""
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_id_and_password_controll)

        findViewById<TextView>(R.id.textViewEnterIDAndPassword).text = "please enter your id and password for " + MainActivity.takenFromMainActivity.nameSelection

        val btnLogin = findViewById<TextView>(R.id.textViewLogin)

        //calling the method userLogin() for login the user
        btnLogin.setOnClickListener(View.OnClickListener {
            userLogin()
        })
    }

    private fun userLogin() {

        val etName = findViewById<EditText>(R.id.editTextPersonID)
        val etPassword = findViewById<EditText>(R.id.editTextPersonPassword)
        //first getting the values
        val username = etName.text.toString()
        val password = etPassword.text.toString()
        val type = MainActivity.takenFromMainActivity.IdSelection
        //validating inputs
        if (TextUtils.isEmpty(username)) {
            etName.error = "Please enter your username"
            etName.requestFocus()
            return
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.error = "Please enter your password"
            etPassword.requestFocus()
            return
        }


        val stringRequest = object : StringRequest(Request.Method.POST, URLs.URL_LOGIN,
            Response.Listener { response ->
                try {
                    //converting response to json object
                    val obj = JSONObject(response)

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_SHORT).show()

                        //getting the user from the response
                        val userJson = obj.getJSONObject("user")

                        //creating a new user object
                        val user = User(
                            userJson.getInt("id"),
                            userJson.getString("username"),
                            userJson.getString("type")
                        )

                        //storing the user in shared preferences
                        SharedPrefManager.getInstance(applicationContext).userLogin(user)
                        //starting the MainActivity
                        finish()
                        takename.takeName = username
                        if(type == "1"){
                            startActivity(Intent(applicationContext, Resident::class.java))
                        }
                        else if(type == "2"){
                            startActivity(Intent(applicationContext, Security::class.java))
                        }
                        else if(type == "3"){
                            startActivity(Intent(applicationContext, Manager::class.java))
                        }
                        else{
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                        }
                    } else {
                        Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_SHORT).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error -> Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show() }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["password"] = password
                params["type"] = type
                return params
            }
        }
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest)
    }
    fun openMainPage(view: View){
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }

}