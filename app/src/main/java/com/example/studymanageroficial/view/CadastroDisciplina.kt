package com.example.studymanageroficial.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.example.studymanageroficial.R
import com.example.studymanageroficial.conect.Conexao
import com.example.studymanageroficial.modelo.Disciplina
import kotlinx.android.synthetic.main.activity_cadastro_disciplina.*

class CadastroDisciplina : AppCompatActivity() {
    val conexao:Conexao by lazy{
        Room.databaseBuilder(this,Conexao::class.java,"DBstudyManager")
            .allowMainThreadQueries().build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_disciplina)

        saveDisciplina.setOnClickListener {
            conexao.DisciplinaDAO().inserir(Disciplina(nomeDisciplina.text.toString(),conteudoDisciplina.text.toString()))
            conexao.DisciplinaDAO().listAll().forEach{ (Log.i("Disciplinas",it.toString()))}
            nomeDisciplina.setText("")
            conteudoDisciplina.setText("")
        }

        buttoListarDisciplinas.setOnClickListener {
            var i = Intent(this,ListDisciplinas::class.java)
            startActivity(i)
        }

    }
}
