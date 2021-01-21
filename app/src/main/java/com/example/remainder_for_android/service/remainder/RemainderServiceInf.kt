package com.example.remainder_for_android.service.remainder

import com.example.remainder_for_android.model.Remainder
import com.example.remainder_for_android.model.RemainderList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface RemainderServiceInf {
    @GET("remainder")
    fun getAll(@Header("Authorization") token: String): Call<RemainderList>
}