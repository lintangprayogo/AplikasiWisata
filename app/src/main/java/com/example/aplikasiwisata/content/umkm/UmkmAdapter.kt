package com.example.aplikasiwisata.content.umkm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.aplikasiwisata.BuildConfig
import com.example.aplikasiwisata.R
import com.example.aplikasiwisata.base.model.Umkm
import com.example.aplikasiwisata.base.ui.BasePageAdapter
import com.example.aplikasiwisata.base.ui.BaseViewHolder
import com.example.aplikasiwisata.base.ui.ListFooterViewHolder
import kotlinx.android.synthetic.main.umkm_layout_item.view.*

class UmkmAdapter(retry: () -> Unit) : BasePageAdapter<Umkm>(retry, ITEM_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == DATA_VIEW_TYPE) {
            val holder =
                UmkmHolder(LayoutInflater.from(parent.context).inflate(mLayout, parent, false))
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
            getItem(position)?.let { (holder as UmkmHolder).bindData(it) }
        } else (holder as ListFooterViewHolder).bind(state)
    }


    inner class UmkmHolder(view: View) : BaseViewHolder<Umkm>(view) {
        override fun bindData(data: Umkm) {
            itemView.umkm_name.text = data.umkmName
            itemView.umkm_owner.text = data.owner
            itemView.umkm_phone.text = data.phone
            val url = BuildConfig.BASE_URL + "/" + BuildConfig.BASE_IMG_URL + "umkm/" + data.logo
            Glide.with(mContext).load(url).apply(
                RequestOptions().transform(RoundedCorners(60)).error(R.drawable.ic_shop)
                    .skipMemoryCache(true).diskCacheStrategy(
                        DiskCacheStrategy.NONE
                    )
            ).into(itemView.logo_umkm)

        }
    }

    companion object {
        private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<Umkm>() {
            override fun areItemsTheSame(oldItem: Umkm, newItem: Umkm): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Umkm, newItem: Umkm): Boolean {
                return oldItem == newItem
            }

        }
    }


}