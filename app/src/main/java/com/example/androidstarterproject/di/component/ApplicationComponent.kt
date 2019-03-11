package com.example.androidstarterproject.di.component


import android.app.Application
import android.content.Context
import com.example.androidstarterproject.data.DataManager
import com.example.androidstarterproject.di.annotations.ApplicationContext
import com.example.androidstarterproject.di.module.ApplicationModule
import com.example.androidstarterproject.root.MvpApp
import com.example.androidstarterproject.rx.EventBus
import dagger.Component
import javax.inject.Singleton

/**
 * Created by rtukpe on 13/03/2018.
 */

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

    val dataManager: DataManager

    fun inject(app: MvpApp)

    val eventBus: EventBus

    @ApplicationContext
    fun context(): Context

    fun application(): Application
}
