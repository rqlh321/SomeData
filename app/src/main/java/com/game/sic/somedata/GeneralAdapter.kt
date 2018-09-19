package com.game.sic.somedata

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class GeneralAdapter<Item : GeneralAdapter.Item, ViewModel>(private val liveData: LiveData<List<Item>>, private val viewModel: ViewModel?) : RecyclerView.Adapter<GeneralAdapter.ViewHolder>() {

    private val items: ArrayList<Item> = ArrayList()

    private val observer: Observer<List<Item>> = Observer { set(it) }

    private fun set(new: List<Item>?) = new?.let {
        items.clear()
        items.addAll(it)
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        liveData.observeForever(observer)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, LinearLayout.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(ItemOffsetDecoration(30))
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        liveData.removeObserver(observer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(viewType, parent, false)
        return GeneralAdapter.ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].res()
    }

    override fun onBindViewHolder(holder: GeneralAdapter.ViewHolder, position: Int) {
        holder.viewDataBinding?.setVariable(BR.item, items[position])
        holder.viewDataBinding?.setVariable(BR.viewmodel, viewModel)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var viewDataBinding: ViewDataBinding? = DataBindingUtil.bind(itemView)
    }

    interface Item {
        fun res(): Int = R.layout.list_item_default
    }
}
