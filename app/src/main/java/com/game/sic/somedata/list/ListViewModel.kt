package com.game.sic.somedata.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import com.game.sic.somedata.GeneralAdapter
import com.game.sic.somedata.R
import com.game.sic.somedata.ThisApplication
import com.game.sic.somedata.repo.local.model.Post
import com.game.sic.somedata.repo.Repo
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import ru.terrakok.cicerone.Router

class ListViewModel : ViewModel() {

    val adapter = GeneralAdapter<Post, ListViewModel>(R.layout.list_item_main, this)

    var progress: MutableLiveData<Boolean> = MutableLiveData()

    private val router: Router = ThisApplication.INSTANCE.cicerone.router

    private var job: Deferred<Unit>? = null

    private val observer: Observer<List<Post>> = Observer { adapter.set(it) }

    init {
        Repo.database.postDao().all().observeForever(observer)
    }

    fun update() {
        job = async(UI) {
            try {
                progress.value = true
                delay(5000)
                async {Repo.update()}
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                progress.value = false
            }
            return@async
        }
    }

    fun click(item: Post) {
        router.newScreenChain(ThisApplication.Screens.ABOUT, item)
    }

    override fun onCleared() {
        Repo.database.postDao().all().removeObserver(observer)
        job?.cancel(Exception("ListViewModel cleared"))
    }

}