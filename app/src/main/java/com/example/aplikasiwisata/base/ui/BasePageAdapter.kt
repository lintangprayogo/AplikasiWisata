package com.example.aplikasiwisata.base.ui

import android.content.Context
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasiwisata.base.service.NetworkState


abstract class BasePageAdapter<T>(
    protected val retry: () -> Unit,
    callback: DiffUtil.ItemCallback<T>
) :
    PagedListAdapter<T, RecyclerView.ViewHolder>(callback) {
    protected var mLayout: Int = 0
    protected lateinit var mContext: Context
    protected lateinit var mListner: (T) -> Unit
    protected val DATA_VIEW_TYPE = 1
    protected val FOOTER_VIEW_TYPE = 2
    protected var state = NetworkState.LOADING

    fun setLayout(mContext: Context, mLayout: Int) {
        this.mContext = mContext
        this.mLayout = mLayout
    }


    fun setListner(mListner: (T) -> Unit) {
        this.mListner = mListner
    }


    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

   fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == NetworkState.LOADING || state == NetworkState.ERROR)
    }

    fun setNetworkState(state: NetworkState) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }


}