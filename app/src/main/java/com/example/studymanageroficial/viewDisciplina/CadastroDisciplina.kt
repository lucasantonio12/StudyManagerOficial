package com.example.studymanageroficial.viewDisciplina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.studymanageroficial.R
import com.example.studymanageroficial.conect.Conexao
import com.example.studymanageroficial.modelo.Disciplina
import com.example.studymanageroficial.shared.SecurityPreferences
import kotlinx.android.synthetic.main.activity_cadastro_disciplina.*

class CadastroDisciplina : AppCompatActivity() {

    private lateinit var sharedPreferences:SecurityPreferences

    val conexao:Conexao by lazy{
        Room.databaseBuilder(this,Conexao::class.java,"DBstudyManager")
            .allowMainThreadQueries().build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_disciplina)

        supportActionBar?.setTitle("Disciplina")
        supportActionBar?.setDisplayShowHomeEnabled(true)

        sharedPreferences = SecurityPreferences(this)
        var user = sharedPreferences.getPreferences("LoginUser")

        floatCadastraDisciplina.setOnClickListener {
            if(checarCamposVaziosDisciplina()){
                var disciplina = Disciplina(nomeDisciplina.text.toString(),conteudoDisciplina.text.toString(),user)
                conexao.DisciplinaDAO().inserir(disciplina)
                conexao.DisciplinaDAO().listDisciplinasUsers(sharedPreferences.getPreferences("LoginUser")).forEach{Log.i("Disciplina",it.toString())}
                nomeDisciplina.setText("")
                conteudoDisciplina.setText("")
                Toast.makeText(this,"Disciplina Cadastrada", Toast.LENGTH_LONG).show()
                finish()
            }else
                Toast.makeText(this,"Existe campos vazios", Toast.LENGTH_LONG).show()

        }
    }

    fun checarCamposVaziosDisciplina():Boolean{
        return !(nomeDisciplina.text.toString() == "" && conteudoDisciplina.text.toString() == "")
    }
}
