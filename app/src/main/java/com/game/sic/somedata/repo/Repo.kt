package com.game.sic.somedata.repo

import android.arch.persistence.room.Room
import android.content.Context
import com.game.sic.somedata.repo.local.AppDatabase
import com.game.sic.somedata.repo.local.dao.PostDao
import com.game.sic.somedata.repo.network.RestService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repo {

    private lateinit var database: AppDatabase

    lateinit var service: RestService

    lateinit var postDao: PostDao
        private set

    fun setup(context: Context) {

        database = Room.databaseBuilder(context, AppDatabase::class.java, "database")
                .build()

        postDao = database.postDao()

        service = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create<RestService>(RestService::class.java)

    }

    suspend fun download() {
        val posts = Repo.service.posts().await().subList(0,1)
        async { Repo.postDao.insert(posts) }.await()
    }

}