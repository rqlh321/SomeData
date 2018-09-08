package com.game.sic.somedata

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class GeneralAdapter<Item, ViewModel>(private val layout: Int, private val viewModel: ViewModel?) : RecyclerView.Adapter<GeneralAdapter.ViewHolder>() {

    private val messages: ArrayList<Item> = ArrayList()

    fun set(new: List<Item>?) = new?.let {
        messages.clear()
        messages.addAll(it)
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, LinearLayout.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, LinearLayout.VERTICAL))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(layout, parent, false)
        return GeneralAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GeneralAdapter.ViewHolder, position: Int) {
        holder.viewDataBinding?.setVariable(BR.item, messages[position])
        holder.viewDataBinding?.setVariable(BR.viewmodel, viewModel)
    }

    override fun getItemCount(): Int = messages.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var viewDataBinding: ViewDataBinding? = DataBindingUtil.bind(itemView)
    }
}
