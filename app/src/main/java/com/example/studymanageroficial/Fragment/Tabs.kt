package com.example.studymanageroficial.Fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class Tabs (fm: FragmentManager) :FragmentPagerAdapter(fm) {

    val disciplinaTab = DisciplinaHome()
    val tarefaTab= TarefaHome()

    override fun getItem(position: Int): Fragment {
        return when (position){
            0->disciplinaTab
            1->tarefaTab
            else -> null!!
        }
    }

    override fun getCount(): Int {
        return 2;
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"Disciplina"
            1->"Tarefa"
            else-> null
        }
    }

}