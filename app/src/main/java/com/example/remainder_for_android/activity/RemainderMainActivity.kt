package com.example.remainder_for_android.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.remainder_for_android.Constatns
import com.example.remainder_for_android.R

class RemainderMainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // get token
        val act = intent.getStringExtra(Constatns.ACT)
        val rft = intent.getStringExtra(Constatns.RFT)
        val spf = this.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)?: return
        with (spf.edit()) {
            putString(Constatns.ACT, act)
            putString(Constatns.RFT, rft)
            apply()
        }
        setContentView(R.layout.activity_remainder_main)
    }

    fun moveToRegist(view: View) {
        val intent = Intent(applicationContext, RegistRemainderActivity::class.java)
        startActivity(intent)
    }

}