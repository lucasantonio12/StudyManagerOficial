package com.example.studymanageroficial.viewTarefa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isGone
import androidx.room.Room
import com.example.studymanageroficial.MainActivity
import com.example.studymanageroficial.R
import com.example.studymanageroficial.conect.Conexao
import com.example.studymanageroficial.shared.SecurityPreferences
import kotlinx.android.synthetic.main.activity_tarefa_detalhada.*

class TarefaDetalhada : AppCompatActivity() {
    val conexao: Conexao by lazy{
        Room.databaseBuilder(this, Conexao::class.java,"DBstudyManager")
            .allowMainThreadQueries().build()
    }

    private lateinit var sharedPreferences: SecurityPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarefa_detalhada)

        sharedPreferences = SecurityPreferences(this)
        supportActionBar?.setTitle("Tarefa")
        supportActionBar?.setDisplayShowHomeEnabled(true)

        var user = sharedPreferences.getPreferences("LoginUser")
        var param = intent.extras
        var id = param?.getInt("id")
        var listaTarefas = conexao.TarefaDAO().listTarefasUser(user)
        var tarefa = listaTarefas.get(id!!.toInt())

        nomeTextTarefa.setText(tarefa.nome)
        descricaoTextTarefa.setText(tarefa.descricao)
        prioridadeTextTarefa.setText(tarefa.prioridade)

        editTarefa.isGone = true
        editNomeTarefa.isGone = true
        editConteudoTarefa.isGone = true
        EditLinear.isGone = true


        floatTarefa.setOnClickListener {
            editNomeTarefa.isGone = false
            editConteudoTarefa.isGone = false
            EditLinear.isGone = false
            editTarefa.isGone = false

            titleTarefaNome.isGone = true
            titleTarefaConteudo.isGone = true
            titleTarefaPrioridade.isGone = true
            nomeTextTarefa.isGone = true
            descricaoTextTarefa.isGone = true
            prioridadeTextTarefa.isGone = true
            floatTarefa.isGone = true
        }

        editNomeTarefa.setText(tarefa.nome)
        editConteudoTarefa.setText(tarefa.descricao)

        editTarefa.setOnClickListener {

            tarefa.nome = editNomeTarefa.text.toString()
            tarefa.descricao = editConteudoTarefa.text.toString()
            tarefa.prioridade = tratamentoRadioButton()

            conexao.TarefaDAO().atualizar(tarefa)
            Toast.makeText(this,"Tarefa Editada",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }


    fun tratamentoRadioButton():String{
        if(baixaButtonEdit.isChecked)
            return "baixa"
        if(mediaButtonEdit.isChecked)
            return "media"
        if(altaButtonEdit.isChecked)
            return "alta"
        else
            return "Erro"
    }
}
