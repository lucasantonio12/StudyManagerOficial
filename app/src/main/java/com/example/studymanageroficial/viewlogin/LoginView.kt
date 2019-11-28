package com.example.studymanageroficial.viewlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studymanageroficial.R
import kotlinx.android.synthetic.main.activity_login_view.*

class LoginView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view)

        registerButton.setOnClickListener {
            var i = Intent(this,CadastroLogin::class.java)
            startActivity(i)
        }
    }
}
