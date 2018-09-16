package com.game.sic.somedata.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.game.sic.somedata.repo.Repo
import com.game.sic.somedata.repo.local.model.Post

class DetailViewModel(id: Long) : ViewModel() {

    var post: LiveData<Post> = Repo.postDao.get(id)

}