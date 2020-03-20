package com.example.aplikasiwisata.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.aplikasiwisata.base.model.Umkm
import com.example.aplikasiwisata.base.service.NetworkService
import com.example.aplikasiwisata.base.service.NetworkState
import com.example.aplikasiwisata.base.source.umkm.UmkmDataSource
import com.example.aplikasiwisata.base.source.umkm.UmkmDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class UmkmListViewModel : ViewModel() {

    private val networkService = NetworkService.getApiService
    var umkmList: LiveData<PagedList<Umkm>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 10
    private val dataSourceFactory: UmkmDataSourceFactory

    init {
        dataSourceFactory =
            UmkmDataSourceFactory(
                compositeDisposable,
                networkService
            )
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize)
            .setEnablePlaceholders(false)
            .build()
        umkmList = LivePagedListBuilder(dataSourceFactory, config).build()
    }


    fun getState(): LiveData<NetworkState> =
        Transformations.switchMap(dataSourceFactory.umkmDataSourceLiveData, UmkmDataSource::state)

    fun retry() {
        dataSourceFactory.umkmDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return umkmList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}