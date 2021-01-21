package com.example.remainder_for_android.view_model

import com.example.remainder_for_android.Constatns
import com.example.remainder_for_android.model.Remainder
import com.example.remainder_for_android.utils.Util
import java.text.SimpleDateFormat
import java.util.*

class RemainderVM(val contents: String, val tag: TagVM, val datetime: Date, val complete: Boolean, val id: Int = -1) {

    constructor(contents: String, tag: TagVM, datetime: String, complete: Boolean, id: Int = -1):
        this(contents, tag, Util().convertStringToDateTimeForRemainder(datetime), complete, id)

    constructor(remainderM: Remainder):
        this(remainderM.contents, TagVM(remainderM.tag), remainderM.datetime, remainderM.complete, remainderM.id)

    fun getDisplayDateTime(): String {
        val df = SimpleDateFormat(Constatns.REMAINDER_DATETIME_DISPLAY_FORMAT)
        return df.format(this.datetime)
    }
}
