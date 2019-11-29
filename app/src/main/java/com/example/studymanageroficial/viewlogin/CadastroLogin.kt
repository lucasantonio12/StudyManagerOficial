package com.example.studymanageroficial.viewlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.studymanageroficial.R
import com.example.studymanageroficial.conect.Conexao
import com.example.studymanageroficial.modelo.Usuario
import kotlinx.android.synthetic.main.activity_cadastro_login.*
import java.util.*
import kotlin.math.log

class CadastroLogin : AppCompatActivity() {
    val conexao: Conexao by lazy{
        Room.databaseBuilder(this, Conexao::class.java,"DBstudyManager")
            .allowMainThreadQueries().build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_login)

        salvarUsuario.setOnClickListener {
            if(camposUsuarioVazio()){
                var usuario = Usuario(nomeTXT.text.toString(),dataTXT.text.toString(),loginTXT.text.toString(),senhaTXT.text.toString())
                conexao.UsuarioDAO().inserir(usuario)
                conexao.UsuarioDAO().listAll().forEach{Log.i("Usuario",it.toString())}

                nomeTXT.setText("")
                dataTXT.setText("")
                loginTXT.setText("")
                senhaTXT.setText("")
            }else
                Toast.makeText(this,"Existe campos vazios",Toast.LENGTH_LONG).show()
        }


    }

    fun camposUsuarioVazio():Boolean{
        return nomeTXT.equals("") && dataTXT.equals("") && loginTXT.equals("") && senhaTXT.equals("")
    }

}
