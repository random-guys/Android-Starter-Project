package com.example.androidstarterproject.di.module


import android.app.Application
import android.content.Context
import com.example.androidstarterproject.data.AppDataManager
import com.example.androidstarterproject.data.DataManager
import com.example.androidstarterproject.di.annotations.ApplicationContext
import com.example.androidstarterproject.rx.EventBus
import com.example.androidstarterproject.rx.RxBus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by rtukpe on 13/03/2018.
 */

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    internal fun provideEventBus(): EventBus = RxBus()

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager = appDataManager
}
