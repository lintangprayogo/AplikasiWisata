@file:Suppress("DEPRECATION")

package com.example.aplikasiwisata.content.umkm

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasiwisata.R
import com.example.aplikasiwisata.base.model.Umkm
import com.example.aplikasiwisata.base.service.NetworkState
import com.example.aplikasiwisata.base.ui.BaseActivity
import com.example.aplikasiwisata.base.viewmodel.UmkmListViewModel
import kotlinx.android.synthetic.main.activity_umkm.*

@Suppress("DEPRECATION")
class UmkmActivity : BaseActivity() {
    private lateinit var umkmAdapter: UmkmAdapter
    private lateinit var viewModel: UmkmListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_umkm)
        viewModel = ViewModelProviders.of(this)
            .get(UmkmListViewModel::class.java)
        initAdapter()
        initState()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.umkm_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun initAdapter() {
        umkmAdapter = UmkmAdapter { viewModel.retry() }
        umkmAdapter.setLayout(this, R.layout.umkm_layout_item)
        umkmAdapter.setListner { baseStartActivity<UmkmDetailActivity, Umkm>(this, it, "data") }
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view.adapter = umkmAdapter
        viewModel.umkmList.observe(this, Observer {
            umkmAdapter.submitList(it)
        })
    }

    private fun initState() {
        txt_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            progress_bar.visibility =
                if (viewModel.listIsEmpty() && state == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility =
                if (viewModel.listIsEmpty() && state == NetworkState.ERROR) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()) {
                umkmAdapter.setNetworkState(state ?: NetworkState.DONE)
            }
        })
    }

}
