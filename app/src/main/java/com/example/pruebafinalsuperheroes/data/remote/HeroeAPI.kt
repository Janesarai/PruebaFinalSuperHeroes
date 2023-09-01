package com.example.pruebafinalsuperheroes.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroeAPI {
    @GET("superheroes/")
    suspend fun getData(): Response<List<Heroe>>


    @GET("superheroes/{id}")
    suspend fun getDetalleHeroe(@Path("id")id:Int): Response<HeroeDetalle>
}