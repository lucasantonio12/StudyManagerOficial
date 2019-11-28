package com.example.studymanageroficial.conect

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.studymanageroficial.dao.DisciplinaDAO
import com.example.studymanageroficial.dao.TarefaDAO
import com.example.studymanageroficial.dao.UsuarioDAO
import com.example.studymanageroficial.modelo.Disciplina
import com.example.studymanageroficial.modelo.Tarefa
import com.example.studymanageroficial.modelo.Usuario

@Database(entities = [Disciplina::class,Tarefa::class,Usuario::class], version = 1)
abstract class Conexao:RoomDatabase() {
    abstract  fun DisciplinaDAO():DisciplinaDAO
    abstract  fun TarefaDAO():TarefaDAO
    abstract fun UsuarioDAO():UsuarioDAO
}