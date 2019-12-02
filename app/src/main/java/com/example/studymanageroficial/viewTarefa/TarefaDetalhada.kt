package com.example.studymanageroficial.viewTarefa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studymanageroficial.R

class TarefaDetalhada : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarefa_detalhada)

        supportActionBar?.setTitle("Tarefa")
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }
}
