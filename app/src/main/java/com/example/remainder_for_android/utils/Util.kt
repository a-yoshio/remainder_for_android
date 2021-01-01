package com.example.remainder_for_android.utils

import com.example.remainder_for_android.Constatns
import java.text.SimpleDateFormat
import java.util.*

class Util {
    fun convertStringToDateTimeForRemainder(strDatetime: String): Date {
        val df = SimpleDateFormat(Constatns.REMAINDER_DATETIME_FORMAT)
        val dt = df.parse(strDatetime)
        return dt
    }

    fun getDatetimeFromString(strDatetime: String): Date {
        val df = SimpleDateFormat(Constatns.REMAINDER_DATETIME_FORMAT)
        val dt = df.parse(strDatetime)
        return dt
    }

}