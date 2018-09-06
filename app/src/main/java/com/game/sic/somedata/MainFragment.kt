package com.game.sic.somedata

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.game.sic.somedata.databinding.FragmentMainBinding
import ru.terrakok.cicerone.android.SupportAppNavigator

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewmodel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

}