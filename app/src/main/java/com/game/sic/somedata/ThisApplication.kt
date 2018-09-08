package com.game.sic.somedata

import android.app.Application
import com.game.sic.somedata.repo.Repo
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class ThisApplication : Application() {

    object Screens {
        const val HOME = "home"
        const val ABOUT = "about"
    }

    lateinit var cicerone: Cicerone<Router>
        private set

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        Repo.setup(this)
        cicerone = Cicerone.create()

    }

    companion object {
        lateinit var INSTANCE: ThisApplication
    }

}