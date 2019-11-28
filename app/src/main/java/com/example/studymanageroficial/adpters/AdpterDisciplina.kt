package com.example.studymanageroficial.adpters

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studymanageroficial.R
import com.example.studymanageroficial.modelo.Disciplina

class AdpterDisciplina (var c:Context, var listDisciplinas:List<Disciplina>) : RecyclerView.Adapter<HolderAdpterDis>(){
    var itemsPendingRemoval = ArrayList<Disciplina>()
    private val handler = Handler()
    var pendingRunnables: HashMap<Disciplina, Runnable> =
        HashMap()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderAdpterDis {
        //Exitem 2 exemplos de layout para ser inflado nessse projeto. Teste os 3.

        //val view = LayoutInflater.from(c).inflate(R.layout.fruta_inflater, parent, false)
        val view = LayoutInflater.from(c).inflate(R.layout.cardisciplinas, parent, false);

        return HolderAdpterDis(view)
    }

    override fun getItemCount(): Int {
        return listDisciplinas.size
    }

    override fun onBindViewHolder(holder: HolderAdpterDis, position: Int) {
        val disciplinaEscolhida = listDisciplinas[position]

        holder.disciplinaView.text = disciplinaEscolhida.nome
        holder.descricaoView.text = disciplinaEscolhida.conteudo


    }


}