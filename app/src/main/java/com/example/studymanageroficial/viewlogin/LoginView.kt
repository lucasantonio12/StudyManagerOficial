package com.example.studymanageroficial.viewlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import com.example.studymanageroficial.MainActivity
import com.example.studymanageroficial.R
import com.example.studymanageroficial.conect.Conexao
import com.example.studymanageroficial.shared.SecurityPreferences
import kotlinx.android.synthetic.main.activity_login_view.*

class LoginView : AppCompatActivity() {

    private lateinit var sharedPreferences:SecurityPreferences

    val conexao: Conexao by lazy{
        Room.databaseBuilder(this, Conexao::class.java,"DBstudyManager")
            .allowMainThreadQueries().build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_view)

        supportActionBar?.setTitle("Study Manager")

        sharedPreferences = SecurityPreferences(this)

        logado()

        registerButton.setOnClickListener {
            var i = Intent(this,CadastroLogin::class.java)
            startActivity(i)
        }

        logarButton.setOnClickListener {


            var usuario = conexao.UsuarioDAO().findByLogin(loginText.text.toString(),senhaText.text.toString())

            if(usuario != null){
                sharedPreferences.setPreferences("LoginUser",usuario.login)
                Toast.makeText(this,"${usuario.login} Seja Bem-Vindo",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,MainActivity::class.java))

            }else
                Toast.makeText(this,"Usuario Não Cadastrado",Toast.LENGTH_LONG).show()
        }
    }

    fun logado(){
        if(!sharedPreferences.getPreferences("LoginUser").equals("")){
            startActivity(Intent(this,MainActivity::class.java))
        }
    }


}
