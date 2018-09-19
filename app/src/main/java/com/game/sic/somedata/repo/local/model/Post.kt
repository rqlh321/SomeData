package com.game.sic.somedata.repo.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.game.sic.somedata.GeneralAdapter
import com.game.sic.somedata.R
import java.util.*

@Entity
data class Post(
          @PrimaryKey var primaryKey: String = UUID.randomUUID().toString()
        , var userId: Int = -1
        , var id: Long = -1
        , var title: String = ""
        , var body: String = ""
        , var state: Int = Random().nextInt(4)
) : GeneralAdapter.Item {

    override fun res(): Int = when (state) {
        STATE_IN_PROGRESS -> R.layout.list_item_main_in_progress
        STATE_CANCELED -> R.layout.list_item_main_canceled
        STATE_READY -> R.layout.list_item_main_done
        else -> R.layout.list_item_default
    }

    companion object {
        const val STATE_CANCELED = 0
        const val STATE_READY = 1
        const val STATE_IN_PROGRESS = 2
    }

}