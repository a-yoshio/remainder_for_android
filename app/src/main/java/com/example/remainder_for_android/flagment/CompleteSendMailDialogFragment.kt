package com.example.remainder_for_android.flagment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.remainder_for_android.R

class CompleteSendMailDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // ダイアログビルダを生成
        val builder = AlertDialog.Builder(activity)
        // ダイアログのタイトルを設定
        builder.setTitle(R.string.complete_send_mail_title)
        // ダイアログのメッセージを設定
        builder.setMessage(R.string.complete_send_mail_explain)
        // Positive Buttonを設定
        builder.setPositiveButton(R.string.dialog_btn_ok, DialogButtonClickListner())
        return builder.create()
    }

    // ダイアログのアクションボタンがタップされた時の処理が記述されたメンバクラス
    private inner class DialogButtonClickListner: DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface, which: Int) {
            dialog.dismiss()
        }
    }
}