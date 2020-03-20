package com.example.aplikasiwisata.base.ui

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecylerView<T, Holder : BaseViewHolder<T>> : RecyclerView.Adapter<Holder>() {
    protected val dataList = mutableListOf<T>()
    protected lateinit var mListener: (T) -> Unit
    protected var mLayout: Int = 0

    fun setLayout(layout: Int) {
        mLayout = layout
    }

    fun setDataList(list: List<T>) {
        dataList.addAll(list)
    }

    fun setListener(listener: (T) -> Unit) {
        mListener = listener
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(dataList[position])
    }
}