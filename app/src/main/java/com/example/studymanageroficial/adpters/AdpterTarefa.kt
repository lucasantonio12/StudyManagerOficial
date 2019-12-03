package com.example.studymanageroficial.adpters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studymanageroficial.R
import com.example.studymanageroficial.modelo.Tarefa

class AdpterTarefa (var c: Context, var listTarefas:List<Tarefa>) : RecyclerView.Adapter<HolderAdpterTar>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderAdpterTar {
        val view = LayoutInflater.from(c).inflate(R.layout.cartarefas,parent,false)
        return HolderAdpterTar(view)
    }

    override fun getItemCount(): Int {
        return  listTarefas.size
    }

    override fun onBindViewHolder(holder: HolderAdpterTar, position: Int) {
        val tarefaEscolhida = listTarefas[position]

        holder.tarefaView.text = tarefaEscolhida.nome
        holder.conteudoView.text = tarefaEscolhida.idDisciplina
        holder.prioridadeTarefa.text = tarefaEscolhida.prioridade
    }

}