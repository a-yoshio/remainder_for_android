package com.example.remainder_for_android.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.remainder_for_android.R
import com.example.remainder_for_android.entity.Remainder
import com.example.remainder_for_android.entity.Tag
import com.example.remainder_for_android.fragment.DeleteConfirmDialogFragment
import com.example.remainder_for_android.utils.Util

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // リマインダーリストを設定
        val remainderLiView = findViewById<ListView>(R.id.remainderList)

        val arrayAdapter = RemaindersArrayAddapter(this, 0).apply {
            add(Remainder("起きる", Tag("家", "ffffff"), "2020/12/30 12:00", false, 1))
            add(Remainder("友達と遊ぶ", Tag("家", "ffffff"), "2020/12/30 16:00", false, 1))
            add(Remainder("ご飯を食べる", Tag("家", "ffffff"), "2020/12/30 18:00", false, 1))
        }

        remainderLiView.adapter = arrayAdapter
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


    // リスト項目を再利用するためのホルダー
    data class ViewHolder(val contents: TextView, val tag: TextView, val datetime: TextView, val complete: Switch, val updateBt: ImageView, val trashBt: ImageView)
    // 自作のリスト項目データを扱えるようにした ArrayAdapter
    private inner class RemaindersArrayAddapter: ArrayAdapter<Remainder> {
        private var inflater: LayoutInflater? = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        constructor(context: Context, resource: Int):super(context, resource) {}

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            var viewHolder : ViewHolder? = null
            var view = convertView

            // 再利用の設定
            if (view == null) {

                view = inflater!!.inflate(R.layout.remainder_item, parent, false)

                viewHolder =
                    ViewHolder(
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
            viewHolder.updateBt.setOnClickListener(RemainderUpdateBtnClcikListner(viewHolder))
            viewHolder.trashBt.setOnClickListener(TrashBtnClickListner())
            return view!!
        }
    }

    private inner class RemainderUpdateBtnClcikListner(val viewData: ViewHolder):View.OnClickListener {
        override fun onClick(view: View?) {
            // タップされた行のデータを取得
            val intent = Intent(applicationContext, UpdateRemainderActivity::class.java)
            val remainderDatetime = Util().getDatetimeFromString(viewData.datetime.text.toString())
            intent.putExtra("contents", viewData.contents.text)
            intent.putExtra("date", remainderDatetime)
            intent.putExtra("time", remainderDatetime)
            intent.putExtra("complete", viewData.complete.isChecked)
            intent.putExtra("tag", viewData.tag.text)
            startActivity(intent)
        }
    }

    private inner class TrashBtnClickListner(): View.OnClickListener {
        override fun onClick(view: View?) {
            val dialogFragment =
                DeleteConfirmDialogFragment()
            // ダイアログ表示
            dialogFragment.show(supportFragmentManager, "DeleteConfirmDialogFragment")
        }
    }
}
