package com.example.aplikasiwisata.content.umkm

import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.aplikasiwisata.BuildConfig
import com.example.aplikasiwisata.R
import com.example.aplikasiwisata.base.model.Umkm
import com.example.aplikasiwisata.base.ui.BaseActivity
import com.example.aplikasiwisata.base.ui.ViewPagerAdapeter
import com.example.aplikasiwisata.content.umkm.product.ProductUmkmFragment
import com.example.aplikasiwisata.content.umkm.profile.ProfileUmkmFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_umkm_detail.*

class UmkmDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_umkm_detail)
        val umkm = getExtraData<Umkm>("data")
        setUpData(umkm)
        setUpPager()
    }

    private fun setUpPager() {
        val adapter = ViewPagerAdapeter(
            this, listOf(
                ProfileUmkmFragment(),
                ProductUmkmFragment()
            ), listOf("Profil", "Produk")
        )
        view_pager.adapter = adapter
        TabLayoutMediator(nav_tab, view_pager, object : TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                tab.setText(adapter.listOfTitle[position])
            }
        }).attach()

    }


    private fun setUpData(umkm: Umkm) {
        umkm_name.text = umkm.umkmName
        umkm_owner.text = umkm.owner
        val url = BuildConfig.BASE_URL + "/" + BuildConfig.BASE_IMG_URL + "umkm/" + umkm.logo
        Glide.with(this).load(url).apply(
            RequestOptions().transform(RoundedCorners(60)).error(R.drawable.ic_shop)
                .skipMemoryCache(true).diskCacheStrategy(
                    DiskCacheStrategy.NONE
                )
        ).into(logo_umkm)
    }
}
