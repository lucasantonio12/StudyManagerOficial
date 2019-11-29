package com.example.studymanageroficial.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tabela_tarefa")
data class Tarefa(var nome:String,var descricao:String,var prioridade:String,var idDisciplina:String,var idUsario:String){
    @PrimaryKey (autoGenerate = true)
    var id = 0
}