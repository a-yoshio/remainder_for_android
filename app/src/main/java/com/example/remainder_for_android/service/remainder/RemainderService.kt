package com.example.remainder_for_android.service.remainder

import android.util.Log
import com.example.remainder_for_android.model.RemainderList
import com.example.remainder_for_android.model.ResultHolder
import com.example.remainder_for_android.service.BaseService
import retrofit2.Response
import java.net.ConnectException

class RemainderService: BaseService() {
    fun getList(act: String): ResultHolder<RemainderList> {
        val service = super.createService(RemainderServiceInf::class.java) as RemainderServiceInf
        try {
            val response: Response<RemainderList> = service.getAll("Bearer "+ act).execute()
            if (!response.isSuccessful) {
                throw ConnectException(response.message())
            } else {
                Log.d("DEBUG", ">>>>> response data: " + response.body())
                return ResultHolder<RemainderList>(response.body() as RemainderList)
            }
        } catch (e: ConnectException) {
            Log.e("ERROR", "API connect failed: " + e.message)
            return ResultHolder<RemainderList>(RemainderList(), false, "API Connect failed")
        }
    }
}