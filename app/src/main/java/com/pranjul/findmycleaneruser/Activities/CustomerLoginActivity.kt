package com.pranjul.findmycleaner.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pranjul.findmycleaneruser.R
import kotlinx.android.synthetic.main.activity_customer_login.*

class CustomerLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_login)
        supportActionBar?.hide()
        signUpCustomerButton.setOnClickListener {
            startActivity(Intent(this@CustomerLoginActivity, CustomerSignUpActivity::class.java))
        }

    }
}