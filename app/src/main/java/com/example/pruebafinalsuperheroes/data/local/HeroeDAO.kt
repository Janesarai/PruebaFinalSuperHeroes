package com.example.pruebafinalsuperheroes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HeroeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroe(heroeEntity: HeroeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroe(heroeEntity: List<HeroeEntity>)

    @Query("Select * from tabla_heroe order by id asc")
    fun getHeroes(): LiveData<List<HeroeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetalleHeroe(heroeDetalleEntity: HeroeDetalleEntity)

    @Query("Select * from tabla_detalleHeroe where id = :id")
    fun getHeroeDetalle(id:Int): LiveData<HeroeDetalleEntity>
}