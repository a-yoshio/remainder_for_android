package com.example.remainder_for_android.fragment

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerDialogFragment: DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(
            context,
            activity as TimePickerDialog.OnTimeSetListener,
            hour,
            minute,
            true)
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        //
    }
}