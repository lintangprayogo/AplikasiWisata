package com.example.aplikasiwisata.base.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.aplikasiwisata.base.service.NetworkService
import com.example.aplikasiwisata.base.service.NetworkState
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

abstract class BaseDataSource<T>(
    protected val networkService: NetworkService,
    protected val compositeDisposable: CompositeDisposable
) :
    PageKeyedDataSource<Int, T>() {
    var state: MutableLiveData<NetworkState> = MutableLiveData()
    private var retryCompletable: Completable? = null

    protected fun updateState(state: NetworkState) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            )
        }
    }


    fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }


}