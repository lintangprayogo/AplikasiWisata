package com.example.aplikasiwisata.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.aplikasiwisata.base.model.Event
import com.example.aplikasiwisata.base.service.NetworkService
import com.example.aplikasiwisata.base.service.NetworkState
import com.example.aplikasiwisata.base.source.event.EventDataSource
import com.example.aplikasiwisata.base.source.event.EventDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class EventListViewModel : ViewModel() {

    private val networkService = NetworkService.getApiService
    var eventList: LiveData<PagedList<Event>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 10
    private val dataSourceFactory: EventDataSourceFactory

    init {
        dataSourceFactory = EventDataSourceFactory(compositeDisposable, networkService)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize)
            .setEnablePlaceholders(false)
            .build()
        eventList = LivePagedListBuilder(dataSourceFactory, config).build()
    }


    fun getState(): LiveData<NetworkState> =
        Transformations.switchMap(dataSourceFactory.eventDataSourceLiveData, EventDataSource::state)

    fun retry() {
        dataSourceFactory.eventDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return eventList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}