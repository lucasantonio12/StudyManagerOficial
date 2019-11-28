package com.example.studymanageroficial.adpters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studymanageroficial.R
import kotlinx.android.synthetic.main.cardisciplinas.view.*

class HolderAdpterDis (v: View) : RecyclerView.ViewHolder(v) {
    val disciplinaView : TextView
    val descricaoView : TextView

    init {
        disciplinaView = v.findViewById(R.id.disciplinaView)
        descricaoView = v.findViewById(R.id.descricaoView)
    }
}