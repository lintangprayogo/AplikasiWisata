package com.example.aplikasiwisata.base.source.umkm

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.aplikasiwisata.base.model.Umkm
import com.example.aplikasiwisata.base.service.NetworkService
import io.reactivex.disposables.CompositeDisposable

class UmkmDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService
) : DataSource.Factory<Int, Umkm>() {

    val umkmDataSourceLiveData = MutableLiveData<UmkmDataSource>()

    override fun create(): DataSource<Int, Umkm> {
        val umkmDataSource = UmkmDataSource(networkService, compositeDisposable)
        umkmDataSourceLiveData.postValue(umkmDataSource)
        return umkmDataSource
    }
}