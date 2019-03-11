package com.example.androidstarterproject.ui.main

import com.example.androidstarterproject.di.annotations.PerActivity
import com.example.androidstarterproject.ui.base.MvpPresenter


/**
 * Created by rtukpe on 21/03/2018.
 */

@PerActivity
interface MainMvpContract<V : MainMvpView> : MvpPresenter<V>
