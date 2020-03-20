package com.example.aplikasiwisata.content.umkm.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aplikasiwisata.base.model.Profile
import com.example.aplikasiwisata.base.ui.BaseRecylerView
import com.example.aplikasiwisata.base.ui.BaseViewHolder
import kotlinx.android.synthetic.main.profile_umkm_item_layoout.view.*

class UmkmProfileAdapter : BaseRecylerView<Profile, UmkmProfileAdapter.UmkmProfileHolder>() {


    inner class UmkmProfileHolder(view: View) : BaseViewHolder<Profile>(view) {
        override fun bindData(data: Profile) {
            itemView.field_title.text = data.field
            itemView.field_value.text = data.value ?: "-"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UmkmProfileHolder {
        return UmkmProfileHolder(
            LayoutInflater.from(parent.context).inflate(mLayout, parent, false)
        )
    }
}