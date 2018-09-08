package com.game.sic.somedata.repo.network

import com.game.sic.somedata.repo.local.model.Post
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface RestService {
    @GET("posts")
    fun posts(): Deferred<List<Post>>
}