package com.example.pruebafinalsuperheroes.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.pruebafinalsuperheroes.data.local.HeroeDAO
import com.example.pruebafinalsuperheroes.data.local.HeroeDetalleEntity
import com.example.pruebafinalsuperheroes.data.local.HeroeEntity
import com.example.pruebafinalsuperheroes.data.remote.HeroeAPI

class Repositorio (private val heroeAPI: HeroeAPI, private val heroeDAO: HeroeDAO){

    fun obtenerHeroesEntity(): LiveData<List<HeroeEntity>> = heroeDAO.getHeroes()

    fun obtenerHeroesDetalle(id: Int): LiveData<HeroeDetalleEntity> = heroeDAO.getHeroeDetalle(id)

    suspend fun getHeroes(){
        try {
            val response = heroeAPI.getData()// llegan los datos
            if (response.isSuccessful) { //llegaron los datos?
                val resp = response.body()
                resp?.let { heroe ->
                    val heroeEntity = heroe.map{it.toEntity()}
                    Log.e("heroeEntity.size",heroeEntity.size.toString() )
                    heroeDAO.insertHeroe(heroeEntity)
                }
            }
        } catch (exeption: Exception) {
            Log.e("catch", "")
        }
    }

   suspend fun getDetalleHeroe(id: Int){

       try {
           val response = heroeAPI.getDetalleHeroe(id)// llegan los datos
           if (response.isSuccessful) {
               val resp = response.body()
               if(resp!= null){
                   val heroeDetalleEntity = resp.toEntity()
                   heroeDAO.insertDetalleHeroe(heroeDetalleEntity)
               }
           }

       } catch (exeption: Exception) {
           Log.e("catch", "")
       }
   }
}