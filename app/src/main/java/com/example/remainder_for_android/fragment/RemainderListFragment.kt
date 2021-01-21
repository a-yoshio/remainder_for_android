package com.example.remainder_for_android.fragment


import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.remainder_for_android.Constatns
import com.example.remainder_for_android.R
import com.example.remainder_for_android.activity.UpdateRemainderActivity
import com.example.remainder_for_android.model.Remainder
import com.example.remainder_for_android.view_model.RemainderVM
import com.example.remainder_for_android.view_model.TagVM
import com.example.remainder_for_android.model.RemainderList
import com.example.remainder_for_android.model.ResultHolder
import com.example.remainder_for_android.request.RemainderData
import com.example.remainder_for_android.request.RequestHolder
import com.example.remainder_for_android.service.remainder.RemainderService

/**
 * A simple [Fragment] subclass.
 */
class RemainderListFragment : Fragment() {
    private var _isLayoutXLarge = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        // 画面のサイズの判定
        super.onActivityCreated(savedInstanceState)
        val updateRemainderFragment = activity?.findViewById<View>(R.id.frameUpdateRemainder)
        if (updateRemainderFragment == null) {
            _isLayoutXLarge = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_remainder_list, container, false)

        // リマインダーリストを設定
        getRemainderList(view).execute()

        return view
    }

    private inner class getRemainderList(val view: View): AsyncTask<RequestHolder<RemainderData>, String, ResultHolder<RemainderList>>() {
        override fun doInBackground(vararg params: RequestHolder<RemainderData>?): ResultHolder<RemainderList> {
            val shp = activity!!.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
            return RemainderService().getList(shp.getString(Constatns.ACT, "") as String)
        }

        override fun onPostExecute(result: ResultHolder<RemainderList>) {
            if(result.isSuccess) {
                // create Adapter
                val arrayAdapter = RemaindersArrayAddapter(view!!.context, 0).apply {
                    result.targetModel.remainder_list.forEach {
                        add(RemainderVM(it))
                    }
                }
                val remainderLiView = view.findViewById<ListView>(R.id.remainderList)

                remainderLiView.adapter = arrayAdapter

            } else {
                Log.d("DEBUG", ">>>>> get: " + result.message)
            }
        }
    }

    // リスト項目を再利用するためのホルダー
    data class ViewHolder(val contents: TextView, val tag: TextView, val datetime: TextView, val complete: Switch, val updateBt: ImageView, val trashBt: ImageView)
    // 自作のリスト項目データを扱えるようにした ArrayAdapter
    private inner class RemaindersArrayAddapter: ArrayAdapter<RemainderVM> {
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
            viewHolder.datetime.text = remainderItem!!.getDisplayDateTime()
            viewHolder.complete.isChecked = remainderItem!!.complete
            viewHolder.updateBt.setOnClickListener(RemainderUpdateBtnClcikListner(viewHolder))
            viewHolder.trashBt.setOnClickListener(TrashBtnClickListner())
            return view!!
        }
    }

    private inner class RemainderUpdateBtnClcikListner(val viewData: ViewHolder):View.OnClickListener {
        override fun onClick(view: View?) {
            // タップされた行のデータを取得
            if(_isLayoutXLarge) {
                // フラグメントトランザクションの開始
                val transaction = fragmentManager?.beginTransaction()
                val remainderUpdateFragment = RemainderUpdateFragment()
                // 引継ぎデータを格納
                val bundle = Bundle()
                bundle.putString("contents", viewData.contents.text.toString())
                bundle.putString("date", "12/25")
                bundle.putString("time", "18:00")
                bundle.putBoolean("complete", viewData.complete.isChecked)
                bundle.putString("tag", viewData.tag.text.toString())
                remainderUpdateFragment.arguments = bundle
                // 生成した更新フラグメントをRemainderUpdateFrameレイアウトに追加する
                transaction?.replace(R.id.frameUpdateRemainder, remainderUpdateFragment)
                // フラグメントトランザクションのコミット
                transaction?.commit()
            } else {
                val intent =
                    Intent(activity?.applicationContext, UpdateRemainderActivity::class.java)
                intent.putExtra("contents", viewData.contents.text)
                intent.putExtra("date", "12/25")
                intent.putExtra("time", "18:00")
                intent.putExtra("complete", viewData.complete.isChecked)
                intent.putExtra("tag", viewData.tag.text)
                startActivity(intent)
            }
        }
    }

    private inner class TrashBtnClickListner(): View.OnClickListener {
        override fun onClick(view: View?) {
            val dialogFragment = DeleteConfirmDialogFragment()
            // ダイアログ表示
            dialogFragment.show(activity!!.supportFragmentManager, "DeleteConfirmDialogFragment")
        }
    }

}
