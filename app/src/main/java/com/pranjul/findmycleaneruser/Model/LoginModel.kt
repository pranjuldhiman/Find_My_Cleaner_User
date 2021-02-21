package com.pranjul.findmycleaner.Model

data class LoginModel(
    val detail: List<Detail>,
    val message: String,
    val status: Int
)