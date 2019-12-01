package com.example.studymanageroficial.Fragment


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
import com.example.studymanageroficial.adpters.AdpterTarefa
import com.example.studymanageroficial.adpters.MyRecyclerViewClickListener
import com.example.studymanageroficial.conect.Conexao
import com.example.studymanageroficial.modelo.Tarefa
import com.example.studymanageroficial.shared.SecurityPreferences
import com.example.studymanageroficial.viewTarefa.CadastroTarefa
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator
import kotlinx.android.synthetic.main.fragment_disciplina_home.*
import kotlinx.android.synthetic.main.fragment_tarefa_home.*


class TarefaHome : Fragment() {

    val conexao: Conexao by lazy{
        Room.databaseBuilder(context!!, Conexao::class.java,"DBstudyManager")
            .allowMainThreadQueries().build()
    }
    private lateinit var sharedPreferences: SecurityPreferences
    var listTarefas:MutableList<Tarefa>?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tarefa_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        floatCadastraTarefa.setOnClickListener {
            startActivity(Intent(context,CadastroTarefa::class.java))
        }

    }

    override fun onResume() {
        super.onResume()

        sharedPreferences = SecurityPreferences(this@TarefaHome.context!!)
        var user = sharedPreferences.getPreferences("LoginUser")

        var adpter = AdpterTarefa(this@TarefaHome.context!!,conexao.TarefaDAO().listTarefasUser(user))
        recyclerTarefa.adapter = adpter

        val layout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        recyclerTarefa.layoutManager = layout

         listTarefas = conexao.TarefaDAO().listTarefasUser(user)

        recyclerTarefa.addOnItemTouchListener(
            MyRecyclerViewClickListener(
                this@TarefaHome.context!!,
                recyclerTarefa,
                object :MyRecyclerViewClickListener.OnItemClickListener{
                    override fun onItemClick(view: View, position: Int) {
                        //var i = Intent(context,)
                    }

                    override fun onItemLongClick(view: View, position: Int) {
                        val remover = listTarefas!![position]
                        listTarefas!!.remove(remover)

                        conexao.TarefaDAO().deletar(remover)

                        recyclerTarefa.adapter = AdpterTarefa(this@TarefaHome.context!!,conexao.TarefaDAO().listTarefasUser(user))

                        val snack = Snackbar.make(
                            recyclerTarefa.parent as View,"Apagando...",Snackbar.LENGTH_LONG)
                            .setAction("Cancelar"){
                                listTarefas!!.add(position,remover)
                                conexao.TarefaDAO().inserir(remover)
                                recyclerTarefa.adapter = AdpterTarefa(this@TarefaHome.context!!,conexao.TarefaDAO().listTarefasUser(user))
                                recyclerTarefa.adapter!!.notifyItemInserted(position)
                            }
                            snack.show()
                    }
                })
        )
        recyclerTarefa.itemAnimator = FlipInTopXAnimator()
    }
}
