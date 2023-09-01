package com.example.pruebafinalsuperheroes.data

import com.example.pruebafinalsuperheroes.data.local.HeroeDetalleEntity
import com.example.pruebafinalsuperheroes.data.local.HeroeEntity
import com.example.pruebafinalsuperheroes.data.remote.Heroe
import com.example.pruebafinalsuperheroes.data.remote.HeroeDetalle

fun Heroe.toEntity(): HeroeEntity = HeroeEntity(
    this.id,
    this.nombre,
    this.origen,
    this.poder,
    this.imagen,
    this.creacion)
fun HeroeDetalle.toEntity(): HeroeDetalleEntity = HeroeDetalleEntity(
    this.id,
    this.nombre,
    this.origen,
    this.poder,
    this.imagen,
    this.creacion,
    this.color,
    this.traduccion)