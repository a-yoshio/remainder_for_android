package com.example.remainder_for_android.service.auth

import android.util.Log
import com.example.remainder_for_android.request.LoginData
import com.example.remainder_for_android.model.Auth
import com.example.remainder_for_android.model.ResultHolder
import com.example.remainder_for_android.service.BaseService
import retrofit2.Response
import java.net.ConnectException

class AuthService: BaseService() {
    fun auth(vararg data: LoginData): ResultHolder<Auth> {
        val loginData: LoginData = data[0]
        Log.d("DEBUG", ">>>>start")
        val service: AuthServiceInf = super.createService(
            AuthServiceInf::class.java) as AuthServiceInf
        try {
            val response: Response<Auth> = service.auth(loginData).execute()
            if (!response.isSuccessful()) {
                if (response.message() == "UNAUTHORIZED") {
                    val msg ="mailaddress or password are different"
                    Log.e("ERROR", msg)
                    return ResultHolder<Auth>(Auth(), false, msg)
                } else {
                    val msg ="API connect failed: " + response.message()
                    Log.e("ERROR","API connect failed: " + response.message())
                    return ResultHolder<Auth>(Auth(), false, "API Connect failed")
                }
            } else {
                return ResultHolder<Auth>(response.body() as Auth)
            }
        } catch(e: ConnectException) {
            Log.e("ERROR", "API connect failed: " + e.message)
            return ResultHolder<Auth>(Auth(), false, "API Connect failed")
        }
    }
}

