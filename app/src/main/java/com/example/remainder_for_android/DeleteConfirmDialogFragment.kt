package com.example.remainder_for_android

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class DeleteConfirmDialogFragment: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // ダイアログビルダを生成
        val builder = AlertDialog.Builder(activity)
        // ダイアログのタイトルを設定
        builder.setTitle(R.string.dialog_title)
        // ダイアログのメッセージを設定
        builder.setMessage(R.string.dialog_msg)
        // Positive Buttonを設定
        builder.setPositiveButton(R.string.dialog_btn_ok, DialogButtonClickListner())
        // Negative Buttonを設定
        builder.setNegativeButton(R.string.dialog_btn_no, DialogButtonClickListner())
        return builder.create()
    }

    // ダイアログのアクションボタンがタップされた時の処理が記述されたメンバクラス
    private inner class DialogButtonClickListner: DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface, which: Int) {
            when(which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    var toastMsg = getString(R.string.delete_complete_toast)
                    Toast.makeText(activity, toastMsg, Toast.LENGTH_LONG).show()
                }
            }
            dialog.dismiss()
        }
    }
}