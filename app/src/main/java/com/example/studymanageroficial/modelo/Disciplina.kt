package com.example.studymanageroficial.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tabela_disciplina")
data class Disciplina(var nome:String, var conteudo:String,var idUsuario:String) {
    @PrimaryKey (autoGenerate = true)
    var id = 0

    override fun toString(): String {
        return nome
    }
}
