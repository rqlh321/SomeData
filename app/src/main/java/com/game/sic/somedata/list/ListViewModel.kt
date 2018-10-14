package com.game.sic.somedata.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import com.game.sic.somedata.GeneralAdapter
import com.game.sic.somedata.R
import com.game.sic.somedata.detail.DetailFragment
import com.game.sic.somedata.repo.Repo
import com.game.sic.somedata.repo.local.model.Post
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class ListViewModel : ViewModel() {

    val adapter = GeneralAdapter(Repo.postDao.all(), this)

    var progress: MutableLiveData<Boolean> = MutableLiveData()

    var error: MutableLiveData<String> = MutableLiveData()

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

    fun click(view: View, item: Post) {
        val bundle = Bundle().apply {
            putLong(DetailFragment.POST_ID, item.id)
        }
        findNavController(view).navigate(R.id.action_listFragment_to_detailFragment, bundle)

    }

    override fun onCleared() {
        job?.cancel(Exception("ListViewModel cleared"))
    }

}