package com.example.pruebafinalsuperheroes.data

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.pruebafinalsuperheroes.data.local.HeroeDAO
import com.example.pruebafinalsuperheroes.data.local.HeroeDataBase
import com.example.pruebafinalsuperheroes.data.local.HeroeEntity
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class RoomDatabaseKtTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var heroeDAO: HeroeDAO
    private lateinit var db: HeroeDataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, HeroeDataBase::class.java).build()
        heroeDAO = db.getHeroeDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertPhone_hapSipyCase_1element() = runBlocking {
        // Given
        val heroeData = HeroeEntity(12,"mimi", "luna", "comer","llll", 123)

        // When
        heroeDAO.insertHeroe(heroeData)

        // Then
        heroeDAO.getHeroes().observeForever {
            Truth.assertThat(it).isNotNull()
            Truth.assertThat(it).isNotEmpty()
            Truth.assertThat(it).hasSize(1)
        }
    }


}


@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T


}
