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
import com.example.remainder_for_android.fragment.DeleteConfirmDialogFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
    
}
