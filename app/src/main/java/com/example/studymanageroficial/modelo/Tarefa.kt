package com.example.studymanageroficial.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tabela_tarefa")
data class Tarefa(var prioridade:String,var nome:String,var descricao:String,var idDisciplina:Int,var idUsario:Int){
    @PrimaryKey (autoGenerate = true)
    var id = 0
}