package com.example.remainder_for_android

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // リマインダーリストを設定
        val remainderLiView = findViewById<ListView>(R.id.remainderList)
        val remainderList: MutableList<MutableMap<String, *>> = mutableListOf()
        var remainder1 = mutableMapOf<String, Any>("id" to 1, "contents" to "起きる", "tag" to "家", "datetime" to "2020/12/30 12:00 Wed", "complete" to false)
        var remainder2 = mutableMapOf<String, Any>("id" to 2, "contents" to "ご飯を食べる", "tag" to "家", "datetime" to "2020/12/30 14:00 Wed", "complete" to false)
        var remainder3 = mutableMapOf<String, Any>("id" to 3, "contents" to "友達とあう", "tag" to "家", "datetime" to "2020/12/30 18:00 Wed", "complete" to false)
        arrayOf(remainder1, remainder2, remainder3).forEach {
            remainderList.add(it)
        }
        val from = arrayOf("contents", "tag", "datetime", "complete") //SimpleAdapte第四引数from用データの用意
        val to = intArrayOf(R.id.remainderContents, R.id.remainderTag, R.id.remainderDatetime, R.id.remainderComplete)//SimpleAdapte第5引数to用データの用意

        val adapter = SimpleAdapter(applicationContext, remainderList, R.layout.remainder_item, from, to)
        remainderLiView.adapter = adapter

        // ゴミ箱ボタンにイベントを設定
//        val trashBtn = findViewById<ImageButton>(R.id.bt_trash)
//        trashBtn.setOnClickListener(TrashBtnClickListner())

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
