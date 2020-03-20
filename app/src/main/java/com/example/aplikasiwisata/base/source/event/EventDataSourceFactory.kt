package com.example.aplikasiwisata.base.source.event

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.aplikasiwisata.base.model.Event

import com.example.aplikasiwisata.base.service.NetworkService
import io.reactivex.disposables.CompositeDisposable

class EventDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val networkService: NetworkService
) : DataSource.Factory<Int, Event>() {

    val eventDataSourceLiveData = MutableLiveData<EventDataSource>()

    override fun create(): DataSource<Int, Event> {
        val eventDataSource = EventDataSource(networkService, compositeDisposable)
        eventDataSourceLiveData.postValue(eventDataSource)
        return eventDataSource
    }
}