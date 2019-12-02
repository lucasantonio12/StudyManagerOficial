package com.example.studymanageroficial.viewDisciplina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isGone
import androidx.room.Room
import com.example.studymanageroficial.MainActivity
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

        supportActionBar?.setTitle("Disciplina")
        supportActionBar?.setDisplayShowHomeEnabled(true)

        var param = intent.extras
        var id = param?.getInt("id")
        var listDisciplinas = conexao.DisciplinaDAO().listAll()
        var disciplina = listDisciplinas.get(id!!.toInt())

        nomeText.setText(disciplina.nome)

        descricaoText.setText(disciplina.conteudo)

        editDisciplina.isGone = true
        editNome.isGone = true
        editDescricao.isGone = true


        floatDisciplina.setOnClickListener {
            editDisciplina.isGone = false
            editNome.isGone = false
            editDescricao.isGone = false

            titleDisciplinaNome.isGone = true
            titleDisciplinaDescricao.isGone = true
            nomeText.isGone = true
            descricaoText.isGone = true
            floatDisciplina.isGone = true

        }

        editNome.setText(disciplina.nome)
        editDescricao.setText(disciplina.conteudo)

        editDisciplina.setOnClickListener {
            disciplina.nome = editNome.text.toString()
            disciplina.conteudo = editDescricao.text.toString()

            conexao.DisciplinaDAO().atualizar(disciplina)
                Toast.makeText(this,"Disciplina Editada",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
