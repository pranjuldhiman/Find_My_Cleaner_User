package com.pranjul.findmycleaner.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pranjul.findmycleaneruser.R

class AdminLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
        supportActionBar?.hide()
    }
}