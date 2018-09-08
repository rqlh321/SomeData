package com.game.sic.somedata.repo.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.game.sic.somedata.repo.local.dao.PostDao
import com.game.sic.somedata.repo.local.model.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}