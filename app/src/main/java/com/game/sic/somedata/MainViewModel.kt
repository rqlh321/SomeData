package com.game.sic.somedata

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import ru.terrakok.cicerone.Router


class MainViewModel : ViewModel() {

    private var router: Router = ThisApplication.INSTANCE.router()

    val adapter = MainAdapter<Post, MainViewModel>(this)
    var progress: MutableLiveData<Boolean> = MutableLiveData()

    private val service: RestService

    private var job: Deferred<Unit>? = null

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create<RestService>(RestService::class.java)
    }

    fun update() {
        job = async(UI) {
            try {
                progress.value = true
                adapter.set(service.posts().await())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                progress.value = false
            }
            return@async
        }
    }

    fun click(item: Post) {
        router.newScreenChain(Screens.ABOUT, item)
    }

    override fun onCleared() {
        job?.cancel(Exception("MainViewModel cleared"))
    }
}