package com.example.remainder_for_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val sendMailBtn = findViewById<Button>(R.id.sendMailBtn)
        sendMailBtn.setOnClickListener(SendMailBtnClickListner())
    }

    private inner class SendMailBtnClickListner: View.OnClickListener {
        override fun onClick(p0: View?) {
            val dialogFragment = CompleteSendMailDialogFragment()
            // ダイアログ表示
            dialogFragment.show(supportFragmentManager, "CompleteSendMailDialogFragment")
        }
    }

}
