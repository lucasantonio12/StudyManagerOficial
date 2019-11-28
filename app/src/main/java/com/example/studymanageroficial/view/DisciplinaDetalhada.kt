package com.example.studymanageroficial.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.studymanageroficial.R
import com.example.studymanageroficial.conect.Conexao
import kotlinx.android.synthetic.main.activity_disciplina_detalhada.*

class DisciplinaDetalhada : AppCompatActivity() {

    val conexao: Conexao by lazy{
        Room.databaseBuilder(this, Conexao::class.java,"DBstudyManager")
            .allowMainThreadQueries().build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disciplina_detalhada)

        var param = intent.extras
        var id = param?.getInt("id")
        var listDisciplinas = conexao.DisciplinaDAO().listAll()
        var disciplina = listDisciplinas.get(id!!.toInt())

        nomeText.setText(disciplina.nome)

    }
}
