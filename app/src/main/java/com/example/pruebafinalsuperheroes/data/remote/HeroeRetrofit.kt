package com.example.pruebafinalsuperheroes.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeroeRetrofit {
    companion object {
        private  const val URL_BASE = "https://y-mariocanedo.vercel.app/"


        fun getRetrofitHeroe() : HeroeAPI {
            val mRetrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return mRetrofit.create(HeroeAPI::class.java)

        }
    }
}