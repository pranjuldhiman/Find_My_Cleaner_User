package com.pranjul.findmycleaneruser.Activities.Packages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pranjul.findmycleaneruser.R
import kotlinx.android.synthetic.main.activity_featured_package.*
import kotlinx.android.synthetic.main.activity_featured_package.chooseBtn

class FeaturedPackageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_featured_package)
        chooseBtn.setOnClickListener {
            showToast("We are Working on payment processor , Please be patient!");

        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}