package com.example.aplikasiwisata.content.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.denzcoskun.imageslider.models.SlideModel
import com.example.aplikasiwisata.R
import com.example.aplikasiwisata.base.ui.BaseFragment
import com.example.aplikasiwisata.content.event.EventActivity
import com.example.aplikasiwisata.content.umkm.UmkmActivity
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBanner()
        setUpMenu()
        activity?.actionBar?.hide()
    }

    private fun setUpBanner() {
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.bandung, "Jelajahi Bandung.", false))
        imageList.add(SlideModel(R.drawable.hotel_banner, "Temukan Hotel.", false))
        imageList.add(SlideModel(R.drawable.event_banner, "Ikuti Event Menarik.", false))
        imageList.add(SlideModel(R.drawable.hotel_banner, "Temukan Hotel.", false))
        image_slider.setImageList(imageList)

    }


    private fun setUpMenu() {
        val list = mutableListOf<Menu>()
        list.add(Menu("Event", R.drawable.ic_event))
        list.add(Menu("UMKM", R.drawable.ic_shop))
        list.add(Menu("Destinasi Wisata", R.drawable.ic_pariwisata))
        list.add(Menu("Budaya", R.drawable.ic_budaya))
        list.add(Menu("Hotel", R.drawable.ic_budaya))
        val adapter = MenuAdapter()
        adapter.setLayout(R.layout.menu_item_layout)
        adapter.setListener {
            when (it.title) {
                "UMKM" -> {
                    baseStartActivity<UmkmActivity>(mActivity)
                }
                "Event"->{
                    baseStartActivity<EventActivity>(mActivity)
                }
                "Budaya" -> {

                }
                "Hotel" -> {

                }
                "Destinasi Wisata" -> {

                }


            }


        }
        adapter.setDataList(list)
        menu_list.adapter = adapter
        menu_list.layoutManager = GridLayoutManager(context, 2)


    }

}
