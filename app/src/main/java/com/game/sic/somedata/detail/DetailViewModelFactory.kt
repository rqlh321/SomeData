package com.game.sic.somedata.detail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class DetailViewModelFactory(private val id: Long) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            DetailViewModel(id) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}