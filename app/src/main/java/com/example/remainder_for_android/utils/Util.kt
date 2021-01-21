package com.example.remainder_for_android.utils

import android.util.Log
import com.example.remainder_for_android.Constatns
import java.text.SimpleDateFormat
import java.util.*

class Util {
    fun convertStringToDateTimeForRemainder(strDatetime: String): Date {
        Log.d("DEBUG", ">>>>get: " + strDatetime)
        val df = SimpleDateFormat(Constatns.REMAINDER_DATETIME_FORMAT)
        val dt = df.parse(strDatetime as String) as Date
        Log.d("DEBUG", ">>>>convert: " + dt)
        return dt
    }

//    fun getDatetimeFromString(strDatetime: String): Date {
//        val df = SimpleDateFormat(Constatns.REMAINDER_DATETIME_FORMAT)
//        val dt = df.parse(strDatetime)
//        return dt
//    }

}