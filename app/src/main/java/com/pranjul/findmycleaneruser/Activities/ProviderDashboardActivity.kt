package com.pranjul.findmycleaneruser.Activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.pranjul.findmycleaneruser.Activities.Packages.PackagesActivity
import com.pranjul.findmycleaneruser.R
import com.pranjul.findmycleaneruser.Utils.SharedPreferenceMethod
import kotlinx.android.synthetic.main.activity_provider_dashboard.*

class ProviderDashboardActivity : AppCompatActivity() {
    lateinit var sharedPreferenceMethod: SharedPreferenceMethod
    lateinit var packagesButton: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_dashboard)
        supportActionBar?.hide()
        packagesButton = findViewById(R.id.packagesButton)
        sharedPreferenceMethod = SharedPreferenceMethod(this)
        userName.text = "Welcome ${sharedPreferenceMethod.userName},\nPlease select a plan!"

        packagesButton.setOnClickListener {
            startActivity(Intent(this@ProviderDashboardActivity, PackagesActivity::class.java))
        }
    }
}