package com.example.remainder_for_android.activity

import android.accounts.AccountManager
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.remainder_for_android.Constatns
import com.example.remainder_for_android.R
import com.example.remainder_for_android.data.Login
import com.example.remainder_for_android.fragment.RemainderListFragment
import com.example.remainder_for_android.fragment.RemainderUpdateFragment
import com.example.remainder_for_android.model.Auth
import com.example.remainder_for_android.model.ResultHolder
import com.example.remainder_for_android.service.AuthService
import com.example.remainder_for_android.utils.ValidationError
import com.example.remainder_for_android.utils.ValidationUtil
import java.lang.Exception
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set click event for Login Submit Button
        val loginBtn = findViewById<Button>(R.id.btnLogin)
        loginBtn.setOnClickListener(LoginSubmitListner())
    }

    fun moveToSignIn(view: View) {
        val intent = Intent(applicationContext, SignInActivity::class.java)
        startActivity(intent)
    }

    private inner class LoginSubmitListner: View.OnClickListener {
        override fun onClick(view: View?) {
            val mailAddress = findViewById<EditText>(R.id.inputMailAddress)
            val password = findViewById<EditText>(R.id.inputPassword)
            val errorText = findViewById<TextView>(R.id.loginError)
            val strMailAddress = mailAddress!!.getText().toString()
            val strPassword = password!!.getText().toString()
            errorText.text = ""

            if (ValidationUtil().isNullString(strMailAddress) || ValidationUtil().isNullString(strPassword) ) {
                errorText.setText("mail address and password are required")
            }
            val loginData = Login(strMailAddress, strPassword)
            LoginTask().execute(loginData)
        }
    }

    private inner class LoginTask: AsyncTask<Login, String, ResultHolder<Auth>>() {
        override fun doInBackground(vararg data: Login?): ResultHolder<Auth> {
            val service = AuthService()
            return service.auth(data[0] as Login)
        }

        override fun onPostExecute(result: ResultHolder<Auth>) {
            if (result.isSuccess) {
                val intent = Intent(applicationContext, RemainderMainActivity::class.java)
                intent.putExtra(Constatns.ACT, result.targetModel.access_token)
                intent.putExtra(Constatns.RFT, result.targetModel.refresh_token)
                startActivity(intent)
            } else {
                val errorText = findViewById<TextView>(R.id.loginError)
                errorText.setText(result.message)
            }
        }
    }
    
}
