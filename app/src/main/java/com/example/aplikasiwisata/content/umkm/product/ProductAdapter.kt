package com.example.aplikasiwisata.content.umkm.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.aplikasiwisata.BuildConfig
import com.example.aplikasiwisata.R
import com.example.aplikasiwisata.base.model.Product
import com.example.aplikasiwisata.base.ui.BasePageAdapter
import com.example.aplikasiwisata.base.ui.BaseViewHolder
import com.example.aplikasiwisata.base.ui.ListFooterViewHolder
import kotlinx.android.synthetic.main.product_item_layout.view.*


class ProductAdapter(retry: () -> Unit) : BasePageAdapter<Product>(retry, ITEM_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == DATA_VIEW_TYPE) {
            val holder =
                ProductHolder(LayoutInflater.from(parent.context).inflate(mLayout, parent, false))
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
            getItem(position)?.let { (holder as ProductHolder).bindData(it) }
        } else (holder as ListFooterViewHolder).bind(state)
    }


    inner class ProductHolder(view: View) : BaseViewHolder<Product>(view) {
        override fun bindData(data: Product) {
            itemView.price.text = data.price.toString()
            itemView.product_name.text = data.name

            val url =
                BuildConfig.BASE_URL + "/" + BuildConfig.BASE_IMG_URL + "/umkm/produk/" + data.photo

            Glide.with(mContext).load(url).apply(
                RequestOptions().error(R.drawable.ic_shop)
                    .skipMemoryCache(true).diskCacheStrategy(
                        DiskCacheStrategy.NONE
                    )
            ).into(itemView.photo_item)

        }
    }

    companion object {
        private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }

        }
    }


}