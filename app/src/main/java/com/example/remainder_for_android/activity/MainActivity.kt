package com.example.remainder_for_android.activity

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.remainder_for_android.Constatns
import com.example.remainder_for_android.R
import com.example.remainder_for_android.request.LoginData
import com.example.remainder_for_android.model.Auth
import com.example.remainder_for_android.model.ResultHolder
import com.example.remainder_for_android.service.auth.AuthService
import com.example.remainder_for_android.service.user.UserService
import com.example.remainder_for_android.utils.ValidationUtil
import com.example.remainder_for_android.utils.ValueEmptyError
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import java.lang.Exception
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils

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
            val loginData = LoginData(strMailAddress, strPassword)
            LoginTask().execute(loginData)
        }
    }

    private inner class LoginTask: AsyncTask<LoginData, String, ResultHolder<Auth>>() {
        override fun doInBackground(vararg data: LoginData?): ResultHolder<Auth> {
            val service =
                AuthService()
            return service.auth(data[0] as LoginData)
        }

        override fun onPostExecute(result: ResultHolder<Auth>) {
            if (result.isSuccess) {
                // 現在のFCM登録トークンを取得する
                FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("DEBUG", "Fetching FCM registration token failed", task.exception)
                        val errorText = findViewById<TextView>(R.id.loginError)
                        errorText.setText("Fetching FCM registration token failed")
                    }

                    // Get new FCM registration token
                    val token = task.result
                    // regist FCM token
                    RegistFCMToken().execute(token, result.targetModel.access_token, result.targetModel.refresh_token)
                })
            } else {
                val errorText = findViewById<TextView>(R.id.loginError)
                errorText.setText(result.message)
            }
        }
    }

    private inner class RegistFCMToken: AsyncTask<String, String, ResultHolder<String>>() {
        var act: String = ""
        var rft: String = ""
        // param[0]: tcm_token:String
        // param[1]: act: String
        // param[2]: rft: String
        override fun doInBackground(vararg param: String?): ResultHolder<String> {
            if (param[0].isNullOrEmpty()) {
                throw ValueEmptyError("tcm_token")
            } else if (param[1].isNullOrEmpty()) {
                throw ValueEmptyError("act")
            } else if (param[2].isNullOrEmpty()) {
                throw ValueEmptyError("rft")
            }
            this.act = param[1] as String
            this.rft = param[2] as String
            return UserService().updateFCMToken(param[1] as String, param[0] as String)
        }

        override fun onPostExecute(result: ResultHolder<String>) {
            if (result.isSuccess) {
                val intent = Intent(applicationContext, RemainderMainActivity::class.java)
                intent.putExtra(Constatns.ACT, this.act)
                intent.putExtra(Constatns.RFT, this.rft)
                startActivity(intent)
            } else {
                val errorText = findViewById<TextView>(R.id.loginError)
                errorText.setText(result.message)
            }
        }
    }
    
}
