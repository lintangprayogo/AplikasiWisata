package com.example.aplikasiwisata.base.source.umkm

import com.example.aplikasiwisata.base.model.Umkm
import com.example.aplikasiwisata.base.service.NetworkService
import com.example.aplikasiwisata.base.service.NetworkState
import com.example.aplikasiwisata.base.source.BaseDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action

class UmkmDataSource(networkService: NetworkService, compositeDisposable: CompositeDisposable) :
    BaseDataSource<Umkm>(networkService, compositeDisposable) {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Umkm>
    ) {
        updateState(NetworkState.LOADING)
        compositeDisposable.add(networkService.getUMKM(0, 10).subscribe(
            { response ->
                updateState(NetworkState.DONE)
                callback.onResult(response.data, null, 10)
            },
            {
                updateState(NetworkState.ERROR)
                setRetry(Action { loadInitial(params, callback) })
            }
        ))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Umkm>) {
        updateState(NetworkState.LOADING)
        compositeDisposable.add(
            networkService.getUMKM(params.key, params.requestedLoadSize)
                .subscribe(
                    { response ->
                        updateState(NetworkState.DONE)
                        callback.onResult(
                            response.data,
                            params.key + 10
                        )
                    },
                    {
                        updateState(NetworkState.ERROR)
                        setRetry(Action { loadAfter(params, callback) })
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Umkm>) {
        TODO("Not yet implemented")
    }

}