package com.game.sic.somedata.repo

import android.arch.persistence.room.Room
import android.content.Context
import com.game.sic.somedata.repo.local.AppDatabase
import com.game.sic.somedata.repo.network.RestService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repo {

    lateinit var database: AppDatabase
        private set

    lateinit var service: RestService
        private set


    fun setup(context: Context) {

        database = Room.databaseBuilder(context, AppDatabase::class.java, "database")
                .build()

        service = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create<RestService>(RestService::class.java)

    }

    suspend fun update() {
        val posts = service.posts().await()
        database.postDao().insert(posts)
    }
}