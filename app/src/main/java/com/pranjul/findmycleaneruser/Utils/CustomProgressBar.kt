package com.pranjul.findmycleaner.Utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.pranjul.findmycleaneruser.R

class CustomProgressBar(private val context: Context?) {
    var popDialog: Dialog? = null

    /*
     * This method display a message or alert for any functionality
     */
    //	show progress bar method
    fun showProgress() {
        popDialog = Dialog(context!!)
        popDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        popDialog!!.setContentView(R.layout.progressbar)
        popDialog!!.window!!.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT))
        popDialog!!.setCancelable(false)
        popDialog!!.show()
    }

    //	Hide progress bar method
    fun hideProgress() {
        if (popDialog != null) {
            popDialog!!.dismiss()
        }
    }
}