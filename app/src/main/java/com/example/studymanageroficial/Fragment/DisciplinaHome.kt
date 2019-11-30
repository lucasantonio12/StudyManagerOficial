package com.example.studymanageroficial.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room

import com.example.studymanageroficial.R
import com.example.studymanageroficial.adpters.AdpterDisciplina
import com.example.studymanageroficial.adpters.MyRecyclerViewClickListener
import com.example.studymanageroficial.conect.Conexao
import com.example.studymanageroficial.modelo.Disciplina
import com.example.studymanageroficial.shared.SecurityPreferences
import com.example.studymanageroficial.viewDisciplina.CadastroDisciplina
import com.example.studymanageroficial.viewDisciplina.DisciplinaDetalhada
import com.example.studymanageroficial.viewlogin.LoginView
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator
import jp.wasabeef.recyclerview.animators.LandingAnimator
import kotlinx.android.synthetic.main.fragment_disciplina_home.*

class DisciplinaHome : Fragment() {
    val conexao: Conexao by lazy{
        Room.databaseBuilder(context!!, Conexao::class.java,"DBstudyManager")
            .allowMainThreadQueries().build()
    }
    private lateinit var sharedPreferences:SecurityPreferences

    var listaDisciplinas:MutableList<Disciplina> ?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disciplina_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        cadastroDisciplinas.setOnClickListener {
            startActivity(Intent(context,CadastroDisciplina::class.java))
        }


    }

    override fun onResume() {
        super.onResume()

        sharedPreferences = SecurityPreferences(this@DisciplinaHome.context!!)
        var user = sharedPreferences.getPreferences("LoginUser")

        var adpter = AdpterDisciplina(this@DisciplinaHome.context!!,conexao.DisciplinaDAO().listDisciplinasUsers(user))
        recyclerDisciplina.adapter = adpter

        val layout = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerDisciplina.layoutManager = layout


         listaDisciplinas = conexao.DisciplinaDAO().listDisciplinasUsers(user)

        recyclerDisciplina.addOnItemTouchListener(
            MyRecyclerViewClickListener(
                this@DisciplinaHome.context!!,
                recyclerDisciplina,
                object : MyRecyclerViewClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        var i = Intent(context,DisciplinaDetalhada::class.java)
                        i.putExtra("id",position)
                        startActivity(i)
                    }

                    override fun onItemLongClick(view: View, position: Int) {
                        val removida = listaDisciplinas!![position]
                        listaDisciplinas!!.remove(removida)

                        conexao.DisciplinaDAO().deletar(removida)

                        recyclerDisciplina.adapter = AdpterDisciplina(this@DisciplinaHome.context!!,conexao.DisciplinaDAO().listDisciplinasUsers(user))

                        recyclerDisciplina.adapter!!.notifyItemRemoved(position)

                        val snack = Snackbar.make(
                            recyclerDisciplina.parent as View,"Apagando... ",Snackbar.LENGTH_LONG )
                            .setAction("Cancelar") {
                                listaDisciplinas!!.add(position, removida)
                                conexao.DisciplinaDAO().inserir(removida)
                                recyclerDisciplina.adapter = AdpterDisciplina(this@DisciplinaHome.context!!,conexao.DisciplinaDAO().listDisciplinasUsers(user))
                                recyclerDisciplina.adapter!!.notifyItemInserted(position)
                            }
                        snack.show()
                    }
                })
        )
        recyclerDisciplina.itemAnimator = FlipInTopXAnimator()
    }

}



