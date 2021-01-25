package com.example.remainder_for_android.service

import com.example.remainder_for_android.config.ApplicationConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


open class BaseService() {
    private val BASE_URL = ApplicationConfig.API_URL
    private fun buildRetrofit(): Retrofit {
        val gson = GsonBuilder().setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").create()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
        return retrofit
    }

    fun createService(serviceInf: Class<*>): Any {
        return buildRetrofit().create(serviceInf)
    }
}