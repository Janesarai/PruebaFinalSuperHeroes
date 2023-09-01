package com.example.pruebafinalsuperheroes.vistas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebafinalsuperheroes.data.Repositorio
import com.example.pruebafinalsuperheroes.data.local.HeroeDataBase
import com.example.pruebafinalsuperheroes.data.remote.HeroeRetrofit
import kotlinx.coroutines.launch

class HeroeVM ( application: Application): AndroidViewModel(application) {
    private val repositorio: Repositorio

    init {
        val heroeAPI = HeroeRetrofit.getRetrofitHeroe()
        val heroeDataBase = HeroeDataBase.getDatabase(application).getHeroeDao()
        repositorio = Repositorio(heroeAPI,heroeDataBase)
    }

    fun heroeLiveData()= repositorio.obtenerHeroesEntity()

    fun detalleLiveData(id:Int)= repositorio.obtenerHeroesDetalle(id)

    fun getTodosHeroes()= viewModelScope.launch {
        repositorio.getHeroes()
    }

    fun getDetallesHeroe(id:Int) = viewModelScope.launch {
        repositorio.getDetalleHeroe(id)
    }
}