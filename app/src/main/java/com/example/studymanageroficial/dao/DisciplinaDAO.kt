package com.example.studymanageroficial.dao

import androidx.room.*
import com.example.studymanageroficial.modelo.Disciplina
import com.example.studymanageroficial.modelo.Usuario

@Dao
interface DisciplinaDAO {
    @Insert
    fun inserir(disciplina: Disciplina):Long

    @Delete
    fun deletar(disciplina: Disciplina):Int

    @Update
    fun atualizar(disciplina: Disciplina):Int

    @Query("SELECT * FROM tabela_disciplina")
    fun listAll(): MutableList<Disciplina>

    @Query("SELECT * FROM tabela_disciplina WHERE nome = :nome")
    fun findByName (nome: String): Disciplina

    @Query("SELECT * FROM tabela_disciplina  WHERE  idUsuario = :idUsuario")
    fun listDisciplinasUsers(idUsuario: String): MutableList<Disciplina>
}