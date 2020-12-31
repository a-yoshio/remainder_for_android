package com.example.remainder_for_android

import android.app.LauncherActivity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.*
import com.example.remainder_for_android.entity.Remainder
import com.example.remainder_for_android.entity.Tag
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_regist_remainder.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("DEBUG", "テスと")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // リマインダーリストを設定
        val remainderLiView = findViewById<ListView>(R.id.remainderList)
//        val remainderList: MutableList<MutableMap<String, *>> = mutableListOf()
//
//        var remainder1 = mutableMapOf<String, Any>("id" to 1, "contents" to "起きる", "tag" to "家", "datetime" to "2020/12/30 12:00 Wed", "complete" to false)
//        var remainder2 = mutableMapOf<String, Any>("id" to 2, "contents" to "ご飯を食べる", "tag" to "家", "datetime" to "2020/12/30 14:00 Wed", "complete" to false)
//        var remainder3 = mutableMapOf<String, Any>("id" to 3, "contents" to "友達とあう", "tag" to "家", "datetime" to "2020/12/30 18:00 Wed", "complete" to false)
//        arrayOf(remainder1, remainder2, remainder3).forEach {
//            remainderList.add(it)
//        }
//        val from = arrayOf("contents", "tag", "datetime", "complete") //SimpleAdapte第四引数from用データの用意
//        val to = intArrayOf(R.id.remainderContents, R.id.remainderTag, R.id.remainderDatetime, R.id.remainderComplete)//SimpleAdapte第5引数to用データの用意
//
//        val adapter = SimpleAdapter(applicationContext, remainderList, R.layout.remainder_item, from, to)
//        remainderLiView.onItemClickListener = RemainderClickListner()
//        remainderLiView.adapter = adapter

        val arrayAdapter = RemaindersArrayAddapter(this, 0).apply {
            add(Remainder("起きる", Tag("家", "ffffff"), "2020/12/30 12:00", false, 1))
            add(Remainder("友達と遊ぶ", Tag("家", "ffffff"), "2020/12/30 16:00", false, 1))
            add(Remainder("ご飯を食べる", Tag("家", "ffffff"), "2020/12/30 18:00", false, 1))
        }

        remainderLiView.adapter = arrayAdapter

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

    private inner class RemainderClickListner:AdapterView.OnItemClickListener {
        override fun onItemClick(parent:AdapterView<*>, view:View, position: Int, id: Long) {
            Log.d("DEBUG", ">>>> get view id: " + view.id)
            // タップされた行のデータを取得
            val item = parent.getItemAtPosition(position) as MutableMap<String, String>
            val contents = item["contents"]
            val datetime = item["datetime"]
//            val complete = item["complete"]
            val tag = item["tag"]
            val intent = Intent(applicationContext, UpdateRemainderActivity::class.java)
            intent.putExtra("contents", contents)
            intent.putExtra("date", "2020/12/31")
            intent.putExtra("time", "12:00")
//            intent.putExtra("complete", complete)
            intent.putExtra("tag", tag)
            startActivity(intent)
        }
    }

    // リスト項目を再利用するためのホルダー
    data class ViewHolder(val contents: TextView, val tag: TextView, val datetime: TextView, val complete: Switch, val updateBt: ImageView, val trashBt: ImageView)
    // 自作のリスト項目データを扱えるようにした ArrayAdapter
    class RemaindersArrayAddapter: ArrayAdapter<Remainder> {
        private var inflater: LayoutInflater? = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        constructor(context: Context, resource: Int):super(context, resource) {}

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            var viewHolder : ViewHolder? = null
            var view = convertView

            // 再利用の設定
            if (view == null) {

                view = inflater!!.inflate(R.layout.remainder_item, parent, false)

                viewHolder = ViewHolder(
                    view.findViewById(R.id.remainderContents),
                    view.findViewById(R.id.remainderTag),
                    view.findViewById(R.id.remainderDatetime),
                    view.findViewById(R.id.remainderComplete),
                    view.findViewById(R.id.bt_update),
                    view.findViewById(R.id.bt_trash)
                )
                view.tag = viewHolder
            } else {
                viewHolder = view.tag as ViewHolder
            }

            // 項目の情報を設定
            val remainderItem = getItem(position)
            viewHolder.contents.text = remainderItem!!.contents
            viewHolder.tag.text = remainderItem!!.tag.title
            viewHolder.datetime.text = remainderItem!!.convertDatetimeToString()
            viewHolder.complete.isChecked = remainderItem!!.complete
            viewHolder.updateBt.setOnClickListener {
                Log.d("DEBUG", "Click Update!")
            }
            viewHolder.trashBt.setOnClickListener{
                Log.d("DEBUG", "Click REMOVE!")
            }
            return view!!
        }
    }
}
