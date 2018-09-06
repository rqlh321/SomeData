package com.game.sic.somedata

import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface RestService {
    @GET("posts")
    fun posts(): Deferred<List<Post>>
}