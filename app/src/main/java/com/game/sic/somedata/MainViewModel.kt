package com.game.sic.somedata

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import ru.terrakok.cicerone.Router


class MainViewModel : ViewModel() {

    private val router: Router = ThisApplication.INSTANCE.cicerone.router

    private val service: RestService = ThisApplication.INSTANCE.service

    private val dao: PostDao = ThisApplication.INSTANCE.database.postDaoDao()

    val adapter = MainAdapter<Post, MainViewModel>(R.layout.list_item_main, this)

    var progress: MutableLiveData<Boolean> = MutableLiveData()

    private var job: Deferred<Unit>? = null

    private val observer: Observer<List<Post>> = Observer { adapter.set(it) }

    init {
        dao.all().observeForever(observer)
    }

    fun update() {
        job = async(UI) {
            try {
                progress.value = true
                val posts = service.posts().await()
                async { dao.insert(posts) }.await()
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
        dao.all().removeObserver(observer)
        job?.cancel(Exception("MainViewModel cleared"))
    }

}