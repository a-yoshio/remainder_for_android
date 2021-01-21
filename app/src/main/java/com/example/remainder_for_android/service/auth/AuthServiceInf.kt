package com.example.remainder_for_android.service.auth

import com.example.remainder_for_android.request.LoginData
import com.example.remainder_for_android.model.Auth
import retrofit2.Call
import retrofit2.http.*

interface AuthServiceInf {
    @Headers("Content-Type:application/json")
    @POST("auth")
    fun auth(@Body loginData: LoginData): Call<Auth>

    @GET("helloworld")
    fun getProperties():
            Call<String>
}