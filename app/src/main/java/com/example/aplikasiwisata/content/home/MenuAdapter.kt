package com.example.aplikasiwisata.content.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aplikasiwisata.base.ui.BaseRecylerView
import com.example.aplikasiwisata.base.ui.BaseViewHolder
import kotlinx.android.synthetic.main.menu_item_layout.view.*

class MenuAdapter :
    BaseRecylerView<Menu, MenuAdapter.MenuHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val holder = MenuHolder(LayoutInflater.from(parent.context).inflate(mLayout, parent, false))
        holder.itemView.setOnClickListener { mListener(dataList[holder.adapterPosition]) }
        return holder
    }

    inner class MenuHolder(view: View) :
        BaseViewHolder<Menu>(view) {
        override fun bindData(data: Menu) {
            itemView.title.text = data.title
            itemView.icon.setImageResource(data.icon)

        }

    }


}