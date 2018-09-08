package com.game.sic.somedata.repo.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Post(
          var userId: Int = -1
        , @PrimaryKey var id: Long = -1
        , var title: String = ""
        , var body: String = ""
)