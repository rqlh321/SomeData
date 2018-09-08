package com.game.sic.somedata.list

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.game.sic.somedata.R
import com.game.sic.somedata.databinding.FragmentListBinding

class ListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentListBinding>(inflater, R.layout.fragment_list, container, false)
        val viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        binding.viewmodel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

}