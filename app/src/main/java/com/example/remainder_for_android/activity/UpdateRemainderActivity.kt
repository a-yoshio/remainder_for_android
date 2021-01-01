package com.example.remainder_for_android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch

class UpdateRemainderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_remainder)

        // 前画面から受け取ったデータを画面に反映
        val contentsEditText = findViewById<EditText>(R.id.contents)
        val dateEditText = findViewById<EditText>(R.id.date)
        val timeEditText = findViewById<EditText>(R.id.time)
        val tagEditText = findViewById<Spinner>(R.id.tag_spinner)
        val completeEditText = findViewById<Switch>(R.id.complete)

        Log.d("DEBUG", ">>>>test")
        val contents = intent.getStringExtra("contents")
        contentsEditText.setText(contents)
        val date = intent.getStringExtra("date")
        dateEditText.setText(date)
        val time = intent.getStringExtra("time")
        timeEditText.setText(time)
        val tag = intent.getStringExtra("tag")
        tagEditText.setSelection(0)
        val complete = intent.getBooleanExtra("complete", false)
        completeEditText.isChecked = complete

        // リスナクラスを設定
        val btClear = findViewById<Button>(R.id.btClear)
        val btCancel = findViewById<Button>(R.id.btCancel)
        val btSubmit = findViewById<Button>(R.id.btSubmit)

        val createActionInstance = createActionListner()
        arrayOf(btSubmit, btCancel, btClear).map { it.setOnClickListener(createActionInstance) }
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
