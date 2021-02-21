package com.pranjul.findmycleaneruser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.pranjul.findmycleaneruser.Activities.Navigation.MainNavigation
import com.pranjul.findmycleaneruser.Activities.ProviderDashboardActivity
import com.pranjul.findmycleaneruser.Activities.Navigation.ProviderNavigation
import com.pranjul.findmycleaneruser.Utils.SharedPreferenceMethod
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {
    lateinit var timer: Timer
    lateinit var sharedPreferenceMethod: SharedPreferenceMethod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_actvity)
        supportActionBar?.hide()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        sharedPreferenceMethod = SharedPreferenceMethod(this)
        Timer().schedule(3000) {
            if (sharedPreferenceMethod.userName == "") {
                startActivity(Intent(this@SplashActivity, MainNavigation::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashActivity, ProviderNavigation::class.java))
                finish()
            }

        }


    }
}