package com.example.remainder_for_android.activity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.remainder_for_android.R
import com.example.remainder_for_android.flagment.DatePickerDialogFragment
import java.util.*

class RegistRemainderActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist_remainder)

        // リスナクラスを設定
        val btClear = findViewById<Button>(R.id.btClear)
        val btCancel = findViewById<Button>(R.id.btCancel)
        val btSubmit = findViewById<Button>(R.id.btSubmit)

        val createActionInstance = createActionListner()
        arrayOf(btSubmit, btCancel, btClear).map { it.setOnClickListener(createActionInstance) }
    }

    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val str = String.format(Locale.US, "%d/%d/%d", year, monthOfYear+1, dayOfMonth)
        val dateEditText = findViewById<TextView>(R.id.date)
        dateEditText.setText(str)
    }


    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerDialogFragment()
        newFragment.show(supportFragmentManager, "datePicker")

    }

    private inner class createActionListner:View.OnClickListener {
        override fun onClick(view:View) {
            val contents = findViewById<EditText>(R.id.contents)
            val date = findViewById<EditText>(R.id.date)
            val time= findViewById<EditText>(R.id.time)
            val tag = findViewById<Spinner>(R.id.tag_spinner)
            val complete = findViewById<Switch>(R.id.complete)

            when(view.id) {
                R.id.btSubmit -> {
                    finish()
                }
                R.id.btCancel -> {
                    finish()
                }
                R.id.btClear -> {
                    contents.setText("")
                    date.setText("")
                    time.setText("")
                    tag.setSelection(0)
                    complete.isChecked=false
                }
            }
        }
    }
}
