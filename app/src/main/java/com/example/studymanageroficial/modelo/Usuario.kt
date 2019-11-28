package com.example.studymanageroficial.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import java.time.OffsetDateTime

@Entity(tableName = "tabela_usuario")
data class Usuario (var nome:String,var date:String,var login:String,var senha:String){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}