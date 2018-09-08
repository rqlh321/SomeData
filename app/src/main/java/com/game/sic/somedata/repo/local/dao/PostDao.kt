package com.game.sic.somedata.repo.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.game.sic.somedata.repo.local.model.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM post")
    fun all(): LiveData<List<Post>>

    @Query("SELECT * FROM post WHERE id = :id")
    fun get(id: Long): LiveData<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Post>)

    @Update
    fun update(post: Post)

    @Update
    fun update(posts: List<Post>)

    @Delete
    fun delete(post: Post)

}