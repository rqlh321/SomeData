package com.game.sic.somedata

import android.app.Application
import android.arch.persistence.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router


class ThisApplication : Application() {

    lateinit var cicerone: Cicerone<Router>
        private set

    lateinit var database: AppDatabase
        private set

    lateinit var service: RestService
        private set

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        cicerone = Cicerone.create()

        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
                .build()

        service = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create<RestService>(RestService::class.java)
    }

    companion object {
        lateinit var INSTANCE: ThisApplication
    }

}