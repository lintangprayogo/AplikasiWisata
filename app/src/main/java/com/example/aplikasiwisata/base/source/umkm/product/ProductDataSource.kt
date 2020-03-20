package com.example.aplikasiwisata.base.source.umkm.product

import com.example.aplikasiwisata.base.model.Product
import com.example.aplikasiwisata.base.service.NetworkService
import com.example.aplikasiwisata.base.service.NetworkState
import com.example.aplikasiwisata.base.source.BaseDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action

class ProductDataSource(
    val umkmID: Int,
    networkService: NetworkService,
    compositeDisposable: CompositeDisposable
) :


    BaseDataSource<Product>(networkService, compositeDisposable) {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Product>
    ) {
        updateState(NetworkState.LOADING)
        compositeDisposable.add(
            networkService.getProduct(0, 10, umkmID).subscribe(
                { response ->
                    updateState(NetworkState.DONE)
                    callback.onResult(response.data, null, 10)
                }
                ,
                {
                    updateState(NetworkState.ERROR)
                    setRetry(Action { loadInitial(params, callback) })
                }
            ))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
        updateState(NetworkState.LOADING)
        compositeDisposable.add(
            networkService.getProduct(params.key, params.requestedLoadSize, umkmID)
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

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
        TODO("Not yet implemented")
    }

}