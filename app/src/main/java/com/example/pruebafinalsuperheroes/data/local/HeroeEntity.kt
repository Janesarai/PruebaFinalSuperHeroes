package com.example.pruebafinalsuperheroes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName ="tabla_heroe")
data class HeroeEntity (
    @PrimaryKey val id: Int,
    val nombre: String,
    val origen: String,
    val poder: String,
    val imagen: String,
    val creacion: Int
)