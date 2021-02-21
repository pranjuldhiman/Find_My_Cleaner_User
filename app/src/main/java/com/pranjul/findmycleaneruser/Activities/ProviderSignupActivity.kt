package com.pranjul.findmycleaneruser.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.pranjul.findmycleaner.Model.LoginModel
import com.pranjul.findmycleaner.Model.UserInfo
import com.pranjul.findmycleaneruser.R
import com.pranjul.findmycleaner.Service.ApiService
import com.pranjul.findmycleaner.Service.ApiUtils
import com.pranjul.findmycleaner.Utils.CustomProgressBar
import com.pranjul.findmycleaneruser.Activities.ProviderAreaActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProviderSignupActivity : AppCompatActivity() {

    lateinit var password: EditText
    lateinit var confirmPassword: EditText
    lateinit var firstNameET: EditText
    lateinit var lastNameET: EditText
    lateinit var email: EditText
    lateinit var phoneET: EditText
    lateinit var apiService: ApiService
    lateinit var signUpButton: Button
    lateinit var signinButton: TextView
    lateinit var customProgressBar: CustomProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_signup)
        supportActionBar?.hide()

        signUpButton = findViewById(R.id.signUpButton)
        firstNameET = findViewById(R.id.firstNameET)
        signinButton = findViewById(R.id.signinButton)
        lastNameET = findViewById(R.id.lastNameET)
        confirmPassword = findViewById(R.id.confirmPasswordET)
        email = findViewById(R.id.emailET)
        password = findViewById(R.id.passwordET)
        phoneET = findViewById(R.id.phoneET)

        apiService = ApiUtils.apiService
        customProgressBar = CustomProgressBar(this)
        signinButton.setOnClickListener {
            startActivity(Intent(this@ProviderSignupActivity, ProviderAreaActivity::class.java))
        }
        signUpButton.setOnClickListener {
            if (email.text.toString().isEmpty()) {
                showToast("Please enter email Address!")
                return@setOnClickListener
            }
            if (!validEmailCheck(email.text.toString())) {
                showToast("Please enter email Address!")
                return@setOnClickListener
            }
            if (firstNameET.text.toString().isEmpty()) {
                showToast("Please enter first name!")
                return@setOnClickListener
            }
            if (lastNameET.text.toString().isEmpty()) {
                showToast("Please enter last name!")
                return@setOnClickListener
            }
            if (phoneET.text.toString().isEmpty()) {
                showToast("Please enter phone number!")
                return@setOnClickListener
            }
            if (password.text.toString().isEmpty()) {
                showToast("Please enter phone number!")
                return@setOnClickListener
            }
            if (password.text.toString().length < 7) {
                showToast("password should be greater than 7 digits!")
                return@setOnClickListener
            }
            if (password.text.toString() != confirmPassword.text.toString()) {
                showToast("password did not match please check!")
                return@setOnClickListener
            } else {
                signUpAPI(email = email.text.toString(),
                    fName = firstNameET.text.toString(),
                    phone = phoneET.text.toString(),
                    password = password.text.toString(),
                    lName = lastNameET.text.toString())

            }
        }

    }

 fun showToast(msg: String) {
        Toast.makeText(this@ProviderSignupActivity, msg, Toast.LENGTH_LONG).show()
    }

    fun signUpAPI(email: String, fName: String, lName: String, phone: String, password: String) {
        customProgressBar.showProgress()
        apiService.signUpAPI(fName = fName,
            lName = lName,
            email = email,
            password = password,
            phone = phone)
            .enqueue(object : Callback<LoginModel> {
                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                    customProgressBar.hideProgress()
                    if (response.body()!!.status == 200) {
                        showToast(response.body()!!.message)
                        startActivity(Intent(this@ProviderSignupActivity,
                            ProviderAreaActivity::class.java))

                    } else {
                        showToast(response.body()!!.message)

                    }
                }

                override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                    Log.e("TAG", "onFailure: " + t.message)
                    customProgressBar.hideProgress()

                }
            }
            )
    }


    fun validEmailCheck(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}