package com.example.remainder_for_android.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.remainder_for_android.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun moveToSignIn(view: View) {
        val intent = Intent(applicationContext, SignInActivity::class.java)
        startActivity(intent)
    }
}
