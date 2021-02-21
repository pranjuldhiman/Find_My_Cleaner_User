package com.pranjul.findmycleaneruser.Activities.Packages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.pranjul.findmycleaneruser.R
import kotlinx.android.synthetic.main.activity_packages.*

class PackagesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_packages)
        free_plans.setOnClickListener {
            startActivity(Intent(this@PackagesActivity,StandardPackageActivity::class.java))
        }
        personal_plan.setOnClickListener {
            startActivity(Intent(this@PackagesActivity,FeaturedPackageActivity::class.java))
        }
        beSpoke_plan.setOnClickListener {
            startActivity(Intent(this@PackagesActivity,BespokePackageActivity::class.java))
        }
    }
}