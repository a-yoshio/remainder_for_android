package com.example.remainder_for_android

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val trashBtn = findViewById<ImageButton>(R.id.bt_trash)
        trashBtn.setOnClickListener(TrashBtnClickListner())

    }

    fun moveToRegist(view: View) {
        val intent = Intent(applicationContext, RegistRemainderActivity::class.java)
        startActivity(intent)
    }

    // TODO: ログイン認証実装したら消す
    fun moveToLogin(view: View) {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
    }

    private inner class TrashBtnClickListner: View.OnClickListener {
        override fun onClick(p0: View?) {
            val dialogFragment = DeleteConfirmDialogFragment()
            // ダイアログ表示
            dialogFragment.show(supportFragmentManager, "DeleteConfirmDialogFragment")
        }
    }
}
