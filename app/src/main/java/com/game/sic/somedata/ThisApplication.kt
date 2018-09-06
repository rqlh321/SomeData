package com.game.sic.somedata

import android.app.Application
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Cicerone



class ThisApplication : Application() {
    private lateinit var cicerone: Cicerone<Router>

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        cicerone = Cicerone.create()
    }

    fun navigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

    fun router(): Router {
        return cicerone.router
    }

    companion object {
        lateinit var INSTANCE: ThisApplication
    }
}