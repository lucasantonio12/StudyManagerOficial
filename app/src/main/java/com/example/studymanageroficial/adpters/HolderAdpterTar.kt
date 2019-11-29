package com.example.studymanageroficial.adpters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studymanageroficial.R

class HolderAdpterTar (v: View) : RecyclerView.ViewHolder(v){
    var tarefaView :TextView
    var conteudoView:TextView
    var prioridadeTarefa:TextView

    init {
        tarefaView = v.findViewById(R.id.tarefaView)
        conteudoView = v.findViewById(R.id.conteudoView)
        prioridadeTarefa = v.findViewById(R.id.prioridadeTarefa)
    }
}