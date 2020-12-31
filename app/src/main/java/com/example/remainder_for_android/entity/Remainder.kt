package com.example.remainder_for_android.entity

import com.example.remainder_for_android.Constatns
import java.text.SimpleDateFormat
import java.util.*

class Remainder(val contents: String, val tag: Tag, val datetime: Date, val complete: Boolean, val id: Int = -1) {
    constructor(contents: String, tag: Tag, datetime: String, complete: Boolean, id: Int = -1):
            this(contents, tag, convertStringToDateTimeForRemainder(datetime), complete, id)

    fun convertDatetimeToString(): String {
        val df = SimpleDateFormat(Constatns.REMAINDER_DATETIME_FORMAT)
        return df.format(this.datetime)
    }
}

fun convertStringToDateTimeForRemainder(strDatetime: String): Date {
    val df = SimpleDateFormat(Constatns.REMAINDER_DATETIME_FORMAT)
    val dt = df.parse(strDatetime)
    return dt
}