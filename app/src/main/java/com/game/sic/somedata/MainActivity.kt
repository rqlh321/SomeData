package com.game.sic.somedata

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportAppNavigator

class MainActivity : FragmentActivity() {

    private val navigator = object : SupportAppNavigator(this, R.id.container) {

        override fun createActivityIntent(context: Context, screenKey: String, data: Any?): Intent? = null

        override fun createFragment(screenKey: String, data: Any?): Fragment = when (screenKey) {
            Screens.HOME -> MainFragment()
            Screens.ABOUT -> Fragment()
            else -> Fragment()
        }

    }

    private var router: Router = ThisApplication.INSTANCE.router()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            router.newRootScreen(Screens.HOME)
        }
    }

    override fun onResume() {
        super.onResume()
        ThisApplication.INSTANCE.navigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        ThisApplication.INSTANCE.navigatorHolder().removeNavigator()
    }
}