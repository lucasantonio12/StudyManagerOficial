package com.example.studymanageroficial.dao

import androidx.room.*
import com.example.studymanageroficial.modelo.Tarefa
import com.example.studymanageroficial.modelo.Usuario

@Dao
interface UsuarioDAO {
    @Insert
    fun inserir(usuario: Usuario):Long

    @Delete
    fun deletar(usuario: Usuario):Int

    @Update
    fun atualizar(usuario: Usuario):Int

    @Query("SELECT * FROM tabela_usuario")
    fun listAll(): MutableList<Usuario>

    @Query("SELECT * FROM tabela_usuario WHERE nome = :nome")
    fun findByName (nome: String): Usuario
}