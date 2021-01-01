package com.example.remainder_for_android.entity

import com.example.remainder_for_android.Constatns
import com.example.remainder_for_android.utils.Util
import java.text.SimpleDateFormat
import java.util.*

class Remainder(val contents: String, val tag: Tag, val datetime: Date, val complete: Boolean, val id: Int = -1) {
    constructor(contents: String, tag: Tag, datetime: String, complete: Boolean, id: Int = -1):
            this(contents, tag, Util().convertStringToDateTimeForRemainder(datetime), complete, id)

    fun convertDatetimeToString(): String {
        val df = SimpleDateFormat(Constatns.REMAINDER_DATETIME_FORMAT)
        return df.format(this.datetime)
    }
}
