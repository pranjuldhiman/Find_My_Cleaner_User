package com.pranjul.findmycleaneruser.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pranjul.findmycleaner.Model.Detail
import com.pranjul.findmycleaner.Model.LoginModel
import com.pranjul.findmycleaner.Service.ApiService
import com.pranjul.findmycleaner.Service.ApiUtils
import com.pranjul.findmycleaner.Utils.CustomProgressBar

import com.pranjul.findmycleaneruser.R
import com.pranjul.findmycleaneruser.Utils.SharedPreferenceMethod
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class ProviderAreaActivity : AppCompatActivity() {
    lateinit var apiservice: ApiService
    lateinit var emailET: EditText
    lateinit var passwordET: EditText
    lateinit var signInButton: Button
    lateinit var signUpButton: TextView
    lateinit var sharedPreferenceMethod: SharedPreferenceMethod
    lateinit var customProgressBar: CustomProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_area)
        supportActionBar?.hide()

        emailET = findViewById(R.id.emailET)
        passwordET = findViewById(R.id.passwordET)
        signInButton = findViewById(R.id.signInButton)
        signUpButton = findViewById(R.id.signUpButton)

        apiservice = ApiUtils.apiService
        sharedPreferenceMethod = SharedPreferenceMethod(this)
        customProgressBar = CustomProgressBar(this)

        signUpButton.setOnClickListener {
            startActivity(
                Intent(
                    this@ProviderAreaActivity,
                    ProviderSignupActivity::class.java
                )
            )
        }
        signInButton.setOnClickListener {

            if (emailET.text.isEmpty()) {
                showToast("Please enter email!")
                return@setOnClickListener
            }
            if (passwordET.text.isEmpty()) {
                showToast("Please enter password!")
                return@setOnClickListener
            }
            if (passwordET.text.toString().length < 6) {
                showToast("Password should be greater than 7")
                return@setOnClickListener
            } else {
                loginAPI(email = emailET.text.toString(), password = passwordET.text.toString())
            }
        }
    }


    fun loginAPI(email: String, password: String) {
        customProgressBar.showProgress()
//        val callback =object : Callback<LoginModel> {
//            override fun onFailure(call: Call<LoginModel>, t: Throwable) {
//
//            }
//
//            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
//            }
//        }
        apiservice.loginAPI(email = email, password = password)
            .enqueue(object : Callback<LoginModel> {
                override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                    customProgressBar.hideProgress()

                    Log.e("TAG", "onFailure: " + t.message)
                }

                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                    customProgressBar.hideProgress()

                    if (response.body()!!.status == 200) {
                        showToast(response.body()!!.message)
                        sharedPreferenceMethod.saveUser(
                            response.body()!!.detail[0].id,
                            response.body()!!.detail[0].fname
                        )
                        val detail = response.body()!!.detail[0]
                        val jsonString = Gson().toJson(detail)
                        PreferenceManager.getDefaultSharedPreferences(this@ProviderAreaActivity)
                            .edit().putString(jsonString, "").apply()
                        startActivity(
                            Intent(
                                this@ProviderAreaActivity,
                                ProviderDashboardActivity::class.java
                            )
                        )
                        finishAffinity()
                    } else {
                        showToast(response.body()!!.message)

                    }
                }
            })

    }

    fun showToast(msg: String) {
        Toast.makeText(this@ProviderAreaActivity, msg, Toast.LENGTH_LONG).show()
    }

    fun validEmailCheck(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}