package com.example.aplikasiwisata.base.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


@Suppress("DEPRECATION")
class ViewPagerAdapeter(
    fa: FragmentActivity,
    val listOfFragment: List<BaseFragment>,
    val listOfTitle: List<String>
) : FragmentStateAdapter(fa) {


    override fun getItemCount(): Int {
        return listOfFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return listOfFragment[position]
    }


}