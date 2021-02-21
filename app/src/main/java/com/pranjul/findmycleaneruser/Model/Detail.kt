package com.pranjul.findmycleaner.Model

import com.google.gson.annotations.SerializedName

data class Detail(

    @SerializedName("email")
    val email: String,

    @SerializedName("fname")
    val fname: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("lname")
    val lname: String,

    @SerializedName("password")
    val password: String
)