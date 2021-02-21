package com.pranjul.findmycleaner.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pranjul.findmycleaneruser.R
import kotlinx.android.synthetic.main.activity_customer_sign_up.*

class CustomerSignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_sign_up)
        supportActionBar?.hide()
        signinButton.setOnClickListener {
            startActivity(Intent(this@CustomerSignUpActivity, CustomerLoginActivity::class.java))
        }
    }
}