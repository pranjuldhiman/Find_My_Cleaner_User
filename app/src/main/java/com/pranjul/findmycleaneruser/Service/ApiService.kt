package com.pranjul.findmycleaner.Service

import com.pranjul.findmycleaner.Model.LoginModel
import com.pranjul.findmycleaner.Model.UserInfo
import com.pranjul.findmycleaneruser.Model.CategoriesList
import com.pranjul.findmycleaneruser.Model.SubCategoryList
import retrofit2.Call
import retrofit2.http.*
import java.net.CacheRequest

interface ApiService {

    @POST("register.php")
    @FormUrlEncoded
    fun signUpAPI(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("fname") fName: String,
        @Field("lname") lName: String,
        @Field("phone") phone: String,
    ): Call<LoginModel>

    @POST("login.php")
    @FormUrlEncoded
    fun loginAPI(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<LoginModel>

    @GET("getCategories.php")
    fun getCategories(@Query("req_all") request: String): Call<CategoriesList>

    //   getCategories List
    @GET("getCategories.php")
    fun getCategoriesList(@Query("req_all") request: String): Call<CategoriesList>

    //   getCategories List
    @GET()
    fun getSubCategoriesList(@Url url: String): Call<SubCategoryList>
}