package com.game.sic.somedata

import android.app.Application
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.game.sic.somedata.repo.Repo
import com.game.sic.somedata.side.CompressWorker
import java.util.concurrent.TimeUnit

class ThisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        Repo.setup(this)

        val compressionWork = PeriodicWorkRequestBuilder<CompressWorker>(500L,TimeUnit.MILLISECONDS)
                .build()
        WorkManager.getInstance().enqueue(compressionWork)
    }

    companion object {
        lateinit var INSTANCE: ThisApplication
    }

}