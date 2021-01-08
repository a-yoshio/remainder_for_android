package com.example.remainder_for_android.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.remainder_for_android.R
import com.example.remainder_for_android.fragment.DatePickerDialogFragment
import com.example.remainder_for_android.fragment.TimePickerDialogFragment
import java.util.*

class UpdateRemainderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_remainder)

//        // 前画面から受け取ったデータを画面に反映
//        val contentsEditText = findViewById<EditText>(R.id.contents)
//        val dateEditText = findViewById<TextView>(R.id.date)
//        val timeEditText = findViewById<TextView>(R.id.time)
//        val tagEditText = findViewById<Spinner>(R.id.tag_spinner)
//        val completeEditText = findViewById<Switch>(R.id.complete)
//
//        Log.d("DEBUG", ">>>>test")
//        val contents = intent.getStringExtra("contents")
//        contentsEditText.setText(contents)
//        val date = intent.getStringExtra("date")
//        dateEditText.setText(date)
//        val time = intent.getStringExtra("time")
//        timeEditText.setText(time)
//        val tag = intent.getStringExtra("tag")
//        tagEditText.setSelection(0)
//        val complete = intent.getBooleanExtra("complete", false)
//        completeEditText.isChecked = complete
//
//        // リスナクラスを設定
//        val btClear = findViewById<Button>(R.id.btClear)
//        val btCancel = findViewById<Button>(R.id.btCancel)
//        val btSubmit = findViewById<Button>(R.id.btSubmit)
//
//        val createActionInstance = createActionListner()
//        arrayOf(btSubmit, btCancel, btClear).map { it.setOnClickListener(createActionInstance) }
    }

//    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
//        val str = String.format(Locale.US, "%d/%d/%d", year, monthOfYear+1, dayOfMonth)
//        val dateTextView = findViewById<TextView>(R.id.date)
//        dateTextView.setText(str)
//    }
//
//
//    fun showDatePickerDialog(v: View) {
//        val newFragment = DatePickerDialogFragment()
//
//        newFragment.show(supportFragmentManager, "datePicker")
//    }
//
//    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
//        val str = String.format(Locale.US, "%d:%d", hourOfDay, minute)
//        val timeTextView = findViewById<TextView>(R.id.time)
//        timeTextView.text = str
//    }
//
//
//    fun showTimePickerDialog(v: View) {
//        val newFragment = TimePickerDialogFragment()
//        newFragment.show(supportFragmentManager, "timePicker")
//    }

//    private inner class createActionListner:View.OnClickListener {
//        override fun onClick(view:View) {
//            val contents = findViewById<EditText>(R.id.contents)
//            val date = findViewById<TextView>(R.id.date)
//            val time= findViewById<TextView>(R.id.time)
//            val tag = findViewById<Spinner>(R.id.tag_spinner)
//            val complete = findViewById<Switch>(R.id.complete)
//
//            when(view.id) {
//                R.id.btSubmit -> {
//                    finish()
//                }
//                R.id.btCancel -> {
//                    finish()
//                }
//                R.id.btClear -> {
//                    contents.setText("")
//                    date.setText("")
//                    time.setText("")
//                    tag.setSelection(0)
//                    complete.isChecked=false
//                }
//            }
//        }
//    }
}
