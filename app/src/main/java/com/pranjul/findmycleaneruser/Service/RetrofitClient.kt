package com.pranjul.findmycleaner.Service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pranjul.findmycleaneruser.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var retrofitBuilder: Retrofit.Builder? = null
    var logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
    var httpClient: OkHttpClient.Builder = OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)

    fun getClient(baseUrl: String?): Retrofit? {
        var retrofit: Retrofit? = null
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(logging)
        }
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()
        if (retrofitBuilder == null) {
            retrofitBuilder = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
        }
        retrofit = retrofitBuilder!!.build()
        return retrofit
    }
}