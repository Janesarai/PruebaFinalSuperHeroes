package com.example.pruebafinalsuperheroes.data

import com.example.pruebafinalsuperheroes.data.remote.Heroe
import org.junit.Assert.assertEquals
import org.junit.Test

class MappereKtTest {

    @Test
    fun toEntity() {
        val heroe  = Heroe(1,"algo", "algun lugar lejano", "volar", "aaaaaggg",333)

        val result = heroe.toEntity()

        assertEquals(heroe.id, result.id)
        assertEquals(heroe.nombre, result.nombre)
        assertEquals(heroe.origen, result.origen)
        assertEquals(heroe.poder, result.poder)
        assertEquals(heroe.imagen,result.imagen)
    }


}