package com.example.androidstarterproject.ui.main

import com.example.androidstarterproject.data.DataManager
import com.example.androidstarterproject.rx.SchedulerProvider
import com.example.androidstarterproject.ui.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by rtukpe on 21/03/2018.
 */

class MainPresenter<V : MainMvpView>
@Inject
constructor(dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), MainMvpContract<V> {

    override fun onViewInitialized() {
        super.onViewInitialized()
    }
}
