package com.pranjul.findmycleaner.Model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("fname") var firstName: String,
    @SerializedName("lname") var lastName: String,
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String,
    @SerializedName("phone") var phone: String,


    )
