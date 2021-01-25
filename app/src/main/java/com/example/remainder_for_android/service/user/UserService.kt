package com.example.remainder_for_android.service.user

import android.util.Log
import com.example.remainder_for_android.model.RemainderList
import com.example.remainder_for_android.model.ResultHolder
import com.example.remainder_for_android.request.FCMTokenData
import com.example.remainder_for_android.service.BaseService
import retrofit2.Response
import java.net.ConnectException

class UserService: BaseService() {
    fun updateFCMToken(act: String, fcmToken: String): ResultHolder<String> {
        Log.d("DEBUG", "START REGIST TOKEN")
        val service = super.createService(UserServiceInf::class.java) as UserServiceInf
        try {
            val requestData = FCMTokenData(fcmToken)
            val response: Response<String> = service.updateFcmToken("Bearer "+ act, requestData).execute()
            if (!response.isSuccessful) {
                throw ConnectException(response.message())
            } else {
                return ResultHolder<String>(response.body() as String)
            }
        } catch (e: ConnectException) {
            Log.e("ERROR", "API connect failed: " + e.message)
            return ResultHolder<String>("", false, "API Connect failed")
        }
    }
}