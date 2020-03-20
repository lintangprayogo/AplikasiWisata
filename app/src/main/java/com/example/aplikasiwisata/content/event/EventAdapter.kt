package com.example.aplikasiwisata.content.event

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasiwisata.base.model.Event
import com.example.aplikasiwisata.base.ui.BasePageAdapter
import com.example.aplikasiwisata.base.ui.BaseViewHolder
import com.example.aplikasiwisata.base.ui.ListFooterViewHolder
import kotlinx.android.synthetic.main.event_item_layout.view.*

class EventAdapter(retry: () -> Unit) : BasePageAdapter<Event>(retry, ITEM_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == DATA_VIEW_TYPE) {
            val holder =
                EventHolder(LayoutInflater.from(parent.context).inflate(mLayout, parent, false))
            holder.itemView.setOnClickListener {
                getItem(holder.adapterPosition)?.let {
                    mListner(it)
                }
            }
            return holder
        } else
            return ListFooterViewHolder.create(retry, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE) {
            getItem(position)?.let { (holder as EventHolder).bindData(it) }
        } else
            (holder as ListFooterViewHolder).bind(state)
    }


    inner class EventHolder(view: View) : BaseViewHolder<Event>(view) {
        @SuppressLint("SetTextI18n")
        override fun bindData(data: Event) {
            itemView.event_name.text = data.event_name
            itemView.date.text = "${data.start_date} - ${data.end_date}"
            itemView
        }

    }

    companion object {
        private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
                return oldItem == newItem
            }

        }
    }


}