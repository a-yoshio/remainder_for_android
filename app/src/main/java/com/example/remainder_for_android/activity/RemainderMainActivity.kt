package com.example.remainder_for_android.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.remainder_for_android.R

class RemainderMainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remainder_main)
    }

    fun moveToRegist(view: View) {
        val intent = Intent(applicationContext, RegistRemainderActivity::class.java)
        startActivity(intent)
    }

}