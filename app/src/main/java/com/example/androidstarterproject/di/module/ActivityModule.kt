package com.example.androidstarterproject.di.module

import android.content.Context
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import dagger.Module
import dagger.Provides
import com.example.androidstarterproject.di.annotations.ActivityContext
import com.example.androidstarterproject.di.annotations.PerActivity
import com.example.androidstarterproject.rx.AppSchedulerProvider
import com.example.androidstarterproject.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by rtukpe on 13/03/2018.
 */

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityContext
    internal fun provideContext(): Context = activity

    @Provides
    internal fun provideActivity(): AppCompatActivity = activity

    // Provide Utility Contexts

    @Provides
    internal fun provideSnapHelper(): SnapHelper = GravitySnapHelper(Gravity.START)

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideLinearLayoutManager(activity: AppCompatActivity): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    internal fun provideGridLayoutManager(activity: AppCompatActivity): GridLayoutManager = GridLayoutManager(activity, 2)
}
