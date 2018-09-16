package com.game.sic.somedata.repo.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Post(
          @PrimaryKey var primaryKey: String = UUID.randomUUID().toString()
        , var userId: Int = -1
        , var id: Long = -1
        , var title: String = ""
        , var body: String = ""
)