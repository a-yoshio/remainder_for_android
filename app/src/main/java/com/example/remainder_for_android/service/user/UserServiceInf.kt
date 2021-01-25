package com.example.remainder_for_android.service.user

import com.example.remainder_for_android.model.RemainderList
import com.example.remainder_for_android.request.FCMTokenData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserServiceInf {
    @Headers("Content-Type:application/json")
    @POST("user/fcm")
    fun updateFcmToken(@Header("Authorization") token: String, @Body data: FCMTokenData): Call<String>
}