package com.example.pruebafinalsuperheroes.data.remote

import com.google.gson.annotations.SerializedName

data class HeroeDetalle (
    val id: Int,
    val nombre: String,
    val origen: String,
    val poder: String,
    @SerializedName("imagenLink")val imagen: String,
    @SerializedName("Año_creacion") val creacion: Int,
    val color: String,
    val traduccion: Boolean
)