package com.example.studymanageroficial.viewTarefa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.room.Room
import com.example.studymanageroficial.R
import com.example.studymanageroficial.conect.Conexao
import com.example.studymanageroficial.modelo.Disciplina
import com.example.studymanageroficial.modelo.Tarefa
import com.example.studymanageroficial.shared.SecurityPreferences
import com.example.studymanageroficial.viewDisciplina.CadastroDisciplina
import kotlinx.android.synthetic.main.activity_cadastro_disciplina.*
import kotlinx.android.synthetic.main.activity_cadastro_tarefa.*

class CadastroTarefa : AppCompatActivity() {

    private lateinit var sharedPreferences: SecurityPreferences

    var selecionada:String? = null

    val conexao: Conexao by lazy{
        Room.databaseBuilder(this, Conexao::class.java,"DBstudyManager")
            .allowMainThreadQueries().build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_tarefa)

        supportActionBar?.setTitle("Tarefa")
        supportActionBar?.setDisplayShowHomeEnabled(true)

        sharedPreferences = SecurityPreferences(this)
        var user = sharedPreferences.getPreferences("LoginUser")

        var disciplinaSelecionada = ""



        var listDisciplinas = conexao.DisciplinaDAO().listDisciplinasUsers(user)

        listDisciplinas.forEach { Log.i("ListandoAsDisciplinas",it.toString()) }

        var disciplinaToSpinnerAdapter = ArrayAdapter<Disciplina>(this, android.R.layout.simple_spinner_item, listDisciplinas)

        disciplinasSipnner.adapter = disciplinaToSpinnerAdapter
        disciplinasSipnner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(adapterView: AdapterView<*>?) {

            }

            override fun onItemSelected(adapterView: AdapterView<*>, view: View?, position: Int, id: Long) {
                disciplinaSelecionada = adapterView.getItemAtPosition(position).toString()
                //Toast.makeText(this@CadastroTarefa, "$selecionada", Toast.LENGTH_SHORT).show()
            }

        }


            floatSaveTarefa.setOnClickListener {
                if(camposVaziosTarefa()){
                var tarefa = Tarefa(tarefaTXT.text.toString() , descricaoTXT.text.toString() ,tratamentoRadioButton(), disciplinaSelecionada,user)
                conexao.TarefaDAO().inserir(tarefa)
                conexao.TarefaDAO().listTarefasUser(user).forEach { Log.i("ListaTarefasA",it.toString()) }
                limparCampo()
                Toast.makeText(this,"Tarefa Cadastrada", Toast.LENGTH_LONG).show()
                finish()
             }else
                 Toast.makeText(this,"Existe campos vazios", Toast.LENGTH_LONG).show()
            }


        addDisciplinas.setOnClickListener {
            startActivity(Intent(this,CadastroDisciplina::class.java))
            finish()
        }
    }


    fun camposVaziosTarefa():Boolean{
        return !(tarefaTXT.text.toString() == "" && descricaoTXT.text.toString() == "")
    }

    fun tratamentoRadioButton():String{
        if(baixaButton.isChecked)
            return "baixa"
        if(mediaButton.isChecked)
            return "media"
        if(altaButton.isChecked)
            return "alta"
        else
            return "Baixa"
    }

    fun limparCampo(){
        tarefaTXT.setText("")
        descricaoTXT.setText("")
    }
}
