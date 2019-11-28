package com.example.studymanageroficial.dao

import androidx.room.*
import com.example.studymanageroficial.modelo.Disciplina
import com.example.studymanageroficial.modelo.Tarefa


@Dao
interface TarefaDAO{
    @Insert
    fun inserir(tarefa: Tarefa):Long

    @Delete
    fun deletar(tarefa: Tarefa):Int

    @Update
    fun atualizar(tarefa: Tarefa):Int

    @Query("SELECT * FROM tabela_tarefa")
    fun listAll(): MutableList<Tarefa>

    @Query("SELECT * FROM tabela_tarefa WHERE nome = :nome")
    fun findByName (nome: String): Tarefa
}