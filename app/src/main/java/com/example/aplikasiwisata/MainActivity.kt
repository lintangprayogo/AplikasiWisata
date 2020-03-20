package com.example.aplikasiwisata

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.aplikasiwisata.base.ui.BaseActivity
import com.example.aplikasiwisata.base.ui.BaseFragment
import com.example.aplikasiwisata.content.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_nav.setOnNavigationItemReselectedListener {
            navMenu(it.itemId)
        }
        bottom_nav.selectedItemId = R.id.home
    }


    private fun navMenu(id: Int) {
        when (id) {
            R.id.akun -> {
                addFragment(HomeFragment())
            }
            R.id.home -> {
                addFragment(HomeFragment())
            }

        }
    }


    @SuppressLint("PrivateResource")
    private fun addFragment(fragment: BaseFragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.frame, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }


}
