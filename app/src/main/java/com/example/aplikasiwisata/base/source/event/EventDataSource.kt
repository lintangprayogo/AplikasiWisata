package com.example.aplikasiwisata.base.source.event

import android.util.Log
import com.example.aplikasiwisata.base.model.Event
import com.example.aplikasiwisata.base.service.NetworkService
import com.example.aplikasiwisata.base.service.NetworkState
import com.example.aplikasiwisata.base.source.BaseDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action

class EventDataSource(networkService: NetworkService, compositeDisposable: CompositeDisposable) :
    BaseDataSource<Event>(networkService, compositeDisposable) {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Event>
    ) {
        updateState(NetworkState.LOADING)
        compositeDisposable.add(
            networkService.getEvent(0, 10).subscribe(
                {
                    updateState(NetworkState.DONE)
                    callback.onResult(it.data, null, 10)
                },
                {
                    updateState(NetworkState.ERROR)
                    setRetry(Action { loadInitial(params, callback) })
                }
            ))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Event>) {
        updateState(NetworkState.LOADING)
        compositeDisposable.add(
            networkService.getEvent(params.key, params.requestedLoadSize).subscribe(
                {
                    updateState(NetworkState.DONE)
                    Log.d("DANJUK",state.value.toString())
                    callback.onResult(it.data, params.key + 10)
                },
                {
                    updateState(NetworkState.ERROR)
                    setRetry(Action { loadAfter(params, callback) })
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Event>) {
        TODO("Not yet implemented")
    }
}