package com.example.aplikasiwisata.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.aplikasiwisata.base.model.Product
import com.example.aplikasiwisata.base.service.NetworkService
import com.example.aplikasiwisata.base.service.NetworkState
import com.example.aplikasiwisata.base.source.umkm.product.ProductDataSource
import com.example.aplikasiwisata.base.source.umkm.product.ProductDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class ProductListViewModel() : ViewModel() {

    private val networkService = NetworkService.getApiService
    lateinit var productList: LiveData<PagedList<Product>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 10
    private lateinit var dataSourceFactory: ProductDataSourceFactory


    fun setUp(umkmID: Int) {
        dataSourceFactory =
            ProductDataSourceFactory(
                umkmID,
                compositeDisposable,
                networkService
            )
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize)
            .setEnablePlaceholders(false)
            .build()
        productList = LivePagedListBuilder(dataSourceFactory, config).build()
    }


    fun getState(): LiveData<NetworkState> = Transformations.switchMap(
        dataSourceFactory.productDataSourceLiveData,
        ProductDataSource::state
    )

    fun retry() {
        dataSourceFactory.productDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return productList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}