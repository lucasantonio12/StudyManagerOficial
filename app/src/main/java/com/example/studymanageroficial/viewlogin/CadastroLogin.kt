package com.example.studymanageroficial.viewlogin

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.studymanageroficial.R
import com.example.studymanageroficial.conect.Conexao
import com.example.studymanageroficial.modelo.Usuario
import kotlinx.android.synthetic.main.activity_cadastro_login.*
import java.util.*


class CadastroLogin : AppCompatActivity() {
    val conexao: Conexao by lazy{
        Room.databaseBuilder(this, Conexao::class.java,"DBstudyManager")
            .allowMainThreadQueries().build()
    }

    var DATE_DIALOG_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_login)

        supportActionBar?.setTitle("Usuário")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        floatingActionButton.setOnClickListener {
            if(camposUsuarioVazio()){
                var usuario = Usuario(nomeTXT.text.toString(),dataTXT.text.toString(),loginTXT.text.toString(),senhaTXT.text.toString())
                conexao.UsuarioDAO().inserir(usuario)
                conexao.UsuarioDAO().listAll().forEach{Log.i("Usuario",it.toString())}
                limparcampos()
                Toast.makeText(this,"Cadastro feito com sucesso",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,LoginView::class.java))
                finish()
            }else
                Toast.makeText(this,"Existe campos vazios",Toast.LENGTH_LONG).show()
        }

       floatingButtonDate.setOnClickListener {
            onClick(it)
       }
    }

    fun camposUsuarioVazio():Boolean{
        return !(nomeTXT.text.toString() == "" && loginTXT.text.toString() == "" && senhaTXT.text.toString() == "" && dataTXT.text.toString() == "")
    }

    fun limparcampos(){
        nomeTXT.setText("")
        dataTXT.setText("")
        loginTXT.setText("")
        senhaTXT.setText("")
    }


    override fun onCreateDialog(id: Int): Dialog? {
        val calendario = Calendar.getInstance()
        val ano = calendario[Calendar.YEAR]
        val mes = calendario[Calendar.MONTH]
        val dia = calendario[Calendar.DAY_OF_MONTH]
        when (id) {
            DATE_DIALOG_ID -> return DatePickerDialog(
                this, mDateSetListener, ano, mes,
                dia
            )
        }
        return null
    }

    private val mDateSetListener =
        OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            val data = (dayOfMonth.toString() + " /"
                    + (monthOfYear + 1).toString() + " /" + year.toString())
            Toast.makeText(
                this,
                "DATA = $data", Toast.LENGTH_SHORT
            )
                .show()

            dataTXT.setText(data)
        }

    fun onClick(v: View) {
        if (v === floatingButtonDate) showDialog(DATE_DIALOG_ID)
    }

}
