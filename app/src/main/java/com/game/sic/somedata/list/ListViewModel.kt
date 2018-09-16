package com.game.sic.somedata.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.game.sic.somedata.GeneralAdapter
import com.game.sic.somedata.R
import com.game.sic.somedata.ThisApplication
import com.game.sic.somedata.repo.Repo
import com.game.sic.somedata.repo.local.model.Post
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import ru.terrakok.cicerone.Router

class ListViewModel : ViewModel() {

    val adapter = GeneralAdapter(Repo.postDao.all(), R.layout.list_item_main, this)

    var progress: MutableLiveData<Boolean> = MutableLiveData()

    var error: MutableLiveData<String> = MutableLiveData()

    private val router: Router = ThisApplication.INSTANCE.cicerone.router

    private var job: Deferred<Unit>? = null

    fun update() {
        job = async(UI) {
            try {
                error.value = ""
                progress.value = true
                Repo.download()
            } catch (e: Exception) {
                error.value = e.message
            } finally {
                progress.value = false
            }
            return@async
        }
    }

    fun click(item: Post) {
        router.newScreenChain(ThisApplication.Screens.ABOUT, item.id)
    }

    override fun onCleared() {
        job?.cancel(Exception("ListViewModel cleared"))
    }

}