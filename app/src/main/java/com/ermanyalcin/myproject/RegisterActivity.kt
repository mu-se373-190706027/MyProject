package com.ermanyalcin.myproject

import android.os.Bundle
import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val buttonRegister = findViewById<TextView>(R.id.buttonRegister)
        val textViewLogin = findViewById<TextView>(R.id.textViewLogin)

        buttonRegister.setOnClickListener(View.OnClickListener {
            //if user pressed on button register
            //here we will register the user to server
            registerUser()
        })

        textViewLogin.setOnClickListener(View.OnClickListener {
            finish()
            startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
        })
    }

    private fun registerUser() {
        val editTextUsername = findViewById<EditText>(R.id.editTextUsername)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val username = editTextUsername.text.toString().trim { it <= ' ' }
        val password = editTextPassword.text.toString().trim { it <= ' ' }

        val radioGroupGender = findViewById<RadioGroup>(R.id.radioGender)
        val typeAsText = (findViewById<View>(radioGroupGender.checkedRadioButtonId) as RadioButton).text.toString()

        if(typeAsText == "Resident"){
            MainActivity.takenFromMainActivity.IdSelection = "1"
        }
        else if(typeAsText == "Security"){
            MainActivity.takenFromMainActivity.IdSelection = "2"
        }

        else if(typeAsText == "Manager"){
            MainActivity.takenFromMainActivity.IdSelection = "3"
        }
        else{
            error("Invalid Type")
        }

        val type = MainActivity.takenFromMainActivity.IdSelection.trim { it <= ' ' }

        //first we will do the validations
        if (TextUtils.isEmpty(username)) {
            editTextUsername.error = "Please enter username"
            editTextUsername.requestFocus()
            return
        }
        if (TextUtils.isEmpty(password)) {
            editTextPassword.error = "Enter a password"
            editTextPassword.requestFocus()
            return
        }
        //val URLRegister = URLs.URL_REGISTER + "?username="+username+"&email="+email+"&password="+password+"&gender="+gender
        //println(URLRegister)
        val stringRequest = object : StringRequest(Request.Method.POST, /*URLRegister*/URLs.URL_REGISTER,
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

                        //starting the MainActivity activity
                        finish()
                        startActivity(Intent(applicationContext, MainActivity::class.java))
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
}