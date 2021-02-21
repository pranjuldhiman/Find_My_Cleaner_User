package com.pranjul.findmycleaneruser.Utils

import android.content.Context
import android.content.SharedPreferences
import com.pranjul.findmycleaner.Utils.Constants

class SharedPreferenceMethod(var context: Context) {
    var sp: SharedPreferences? = null
    fun saveUser(userIdLogin: String?, admin_name: String?) {
        sp = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)
        val sp_editior = sp!!.edit()
        sp_editior.putString("userIdLogin", userIdLogin)
        sp_editior.putString("admin_name", admin_name)
        sp_editior.apply()
    }

    val jWTToken: String?
        get() {
            sp = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)
            val sp_editior = sp!!.edit()
            return sp!!.getString("jwt", "")
        }

    //        SharedPreferences.Editor sp_editior = sp.edit();
    val userName: String?
        get() {
            sp = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)
            //        SharedPreferences.Editor sp_editior = sp.edit();
            return sp!!.getString("admin_name", "")
        }

    companion object {
        var spUser_id: String? = null
    }
}