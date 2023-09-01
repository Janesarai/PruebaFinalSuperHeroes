package com.example.pruebafinalsuperheroes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tabla_detalleHeroe")
class HeroeDetalleEntity (
    @PrimaryKey val id: Int,
    val nombre: String,
    val origen: String,
    val poder: String,
    val imagen: String,
    val creacion: Int,
    val color: String,
    val traduccion: Boolean
)