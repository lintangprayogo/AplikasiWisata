package com.example.aplikasiwisata.base.source.umkm.product

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.aplikasiwisata.base.model.Product
import com.example.aplikasiwisata.base.service.NetworkService
import io.reactivex.disposables.CompositeDisposable

class ProductDataSourceFactory(
    private val umkmID: Int,
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService
) : DataSource.Factory<Int, Product>() {

    val productDataSourceLiveData = MutableLiveData<ProductDataSource>()

    override fun create(): DataSource<Int, Product> {
        val productDataSource = ProductDataSource(umkmID, networkService, compositeDisposable)
        productDataSourceLiveData.postValue(productDataSource)
        return productDataSource
    }
}