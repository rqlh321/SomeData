package com.game.sic.somedata

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AlertDialog
import com.game.sic.somedata.list.ListFragment
import ru.terrakok.cicerone.android.SupportAppNavigator

class MainActivity : FragmentActivity() {

    private val navigator = object : SupportAppNavigator(this, R.id.container) {

        override fun createActivityIntent(context: Context, screenKey: String, data: Any?): Intent? = null

        override fun createFragment(screenKey: String, data: Any?): Fragment = when (screenKey) {
            ThisApplication.Screens.HOME -> ListFragment()
            ThisApplication.Screens.ABOUT -> Fragment()
            else -> Fragment()
        }

        override fun showSystemMessage(message: String?) {
            AlertDialog.Builder(this@MainActivity)
                    .setMessage(message)
                    .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
                    .show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            ThisApplication.INSTANCE.cicerone.router.newRootScreen(ThisApplication.Screens.HOME)
        }
    }

    override fun onResume() {
        super.onResume()
        ThisApplication.INSTANCE.cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        ThisApplication.INSTANCE.cicerone.navigatorHolder.removeNavigator()
    }

}