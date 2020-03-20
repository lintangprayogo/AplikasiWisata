@file:Suppress("DEPRECATION")

package com.example.aplikasiwisata.content.umkm.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aplikasiwisata.R
import com.example.aplikasiwisata.base.model.Umkm
import com.example.aplikasiwisata.base.service.NetworkState
import com.example.aplikasiwisata.base.ui.BaseFragment
import com.example.aplikasiwisata.base.viewmodel.ProductListViewModel
import kotlinx.android.synthetic.main.fragment_product_umkm.*


@Suppress("DEPRECATION")
class ProductUmkmFragment : BaseFragment() {
    private lateinit var viewModel: ProductListViewModel
    private lateinit var productAdapter: ProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_product_umkm, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val umkm = getExtraData<Umkm>("data")

        viewModel = ViewModelProviders.of(mActivity).get(ProductListViewModel::class.java)
        viewModel.setUp(umkm.id)
        setUpAdapter()
        initState()
    }


    private fun setUpAdapter() {
        productAdapter = ProductAdapter {}
        context?.let { productAdapter.setLayout(it, R.layout.product_item_layout) }
        productAdapter.setListner { Log.d("UMKM Detail", it.name?:"kosong") }
        product_rv.layoutManager = GridLayoutManager(context, 2)
        product_rv.adapter = productAdapter
        viewModel.productList.observe(viewLifecycleOwner, Observer {
            productAdapter.submitList(it)
        })
    }

    private fun initState() {
        txt_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(viewLifecycleOwner, Observer { state ->
            progress_bar.visibility =
                if (viewModel.listIsEmpty() && state == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility =
                if (viewModel.listIsEmpty() && state == NetworkState.ERROR) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()) {
                productAdapter.setNetworkState(state ?: NetworkState.DONE)
            }
        })
    }


}
