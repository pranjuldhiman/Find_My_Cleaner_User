package com.pranjul.findmycleaner.Service

import com.pranjul.findmycleaner.Service.RetrofitClient.getClient

object ApiUtils {
    const val BASE_URL = "https://gadgetzguy.com/restapi/"
    val apiService: ApiService
        get() = getClient(BASE_URL)!!.create(ApiService::class.java)
}