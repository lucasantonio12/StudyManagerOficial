package com.example.studymanageroficial.viewlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.studymanageroficial.R
import com.example.studymanageroficial.conect.Conexao
import com.example.studymanageroficial.shared.SecurityPreferences
import com.example.studymanageroficial.view.ListDisciplinas
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

        var listaDeUsuario = conexao.UsuarioDAO().listAll()

        sharedPreferences = SecurityPreferences(this)

        registerButton.setOnClickListener {
            var i = Intent(this,CadastroLogin::class.java)
            startActivity(i)
        }

        logarButton.setOnClickListener {

            //var id = 0
            var usuario = conexao.UsuarioDAO().findByLogin(loginText.text.toString(),senhaText.text.toString())

            if(usuario.login.equals(loginText.text.toString()) && usuario.senha.equals(senhaText.text.toString())){
                var i = Intent(this,ListDisciplinas::class.java)
                sharedPreferences.setPreferences("IdUser", usuario.id.toString())
                sharedPreferences.setPreferences("LoginUser",usuario.login.toString())
                //i.putExtra("id",id)
                startActivity(i)
            }else
                Toast.makeText(this,"Usuario Não Cadastrado",Toast.LENGTH_LONG).show()
        }

    }
}
