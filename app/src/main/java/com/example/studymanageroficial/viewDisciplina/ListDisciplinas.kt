package com.example.studymanageroficial.viewDisciplina

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.studymanageroficial.R
import com.example.studymanageroficial.adpters.AdpterDisciplina
import com.example.studymanageroficial.adpters.MyRecyclerViewClickListener
import com.example.studymanageroficial.conect.Conexao
import com.example.studymanageroficial.modelo.Disciplina
import com.example.studymanageroficial.shared.SecurityPreferences
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_list_disciplinas.*

class ListDisciplinas : AppCompatActivity() {
    private lateinit var sharedPreferences: SecurityPreferences

    val conexao: Conexao by lazy{
        Room.databaseBuilder(this, Conexao::class.java,"DBstudyManager")
            .allowMainThreadQueries().build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_disciplinas)

        var listaDisciplinas:MutableList<Disciplina> = conexao.DisciplinaDAO().listAll()

        var adpter = AdpterDisciplina(this,conexao.DisciplinaDAO().listAll())
        recycleView.adapter = adpter

        val layout = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recycleView.layoutManager = layout

        recycleView.addOnItemTouchListener(
                    MyRecyclerViewClickListener(
                    this@ListDisciplinas,
                    recycleView,
                    object : MyRecyclerViewClickListener.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            var i = Intent(baseContext,DisciplinaDetalhada::class.java)
                            i.putExtra("id",position)
                            startActivity(i)
                        }

                    override fun onItemLongClick(view: View, position: Int) {
                        val removida = listaDisciplinas[position]
                        listaDisciplinas.remove(removida)

                        conexao.DisciplinaDAO().deletar(removida)

                        recycleView.adapter!!.notifyItemRemoved(position)

                        recycleView.adapter = AdpterDisciplina(baseContext,conexao.DisciplinaDAO().listAll())

                        val snack = Snackbar.make(
                            recycleView.parent as View,"Apagando... ",Snackbar.LENGTH_LONG )
                            .setAction("Cancelar") {
                                listaDisciplinas.add(position, removida)
                                conexao.DisciplinaDAO().inserir(removida)
                                recycleView.adapter = AdpterDisciplina(baseContext,conexao.DisciplinaDAO().listAll())
                                recycleView.adapter!!.notifyItemInserted(position)
                            }
                        snack.show()
                    }
                })
        )

        recycleView.itemAnimator = DefaultItemAnimator()
        //recyclerview.itemAnimator = LandingAnimator()
        //recyclerview.itemAnimator = FlipInTopXAnimator()
    }
}

