package com.example.studymanageroficial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studymanageroficial.view.CadastroDisciplina
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cadastro.setOnClickListener {
            var i = Intent(this,CadastroDisciplina::class.java)
            startActivity(i)
        }
    }
}
