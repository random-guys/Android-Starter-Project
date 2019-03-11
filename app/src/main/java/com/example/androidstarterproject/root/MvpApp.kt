package com.example.androidstarterproject.root

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.androidstarterproject.utils.Log
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.example.androidstarterproject.di.component.ApplicationComponent
import com.example.androidstarterproject.di.component.DaggerApplicationComponent
import com.example.androidstarterproject.di.module.ApplicationModule

class MvpApp : Application() {

    // Needed to replace the component with a test specific one
    var component: ApplicationComponent? = null

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        // Dagger 2

        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        component!!.inject(this)

        // Timber

        Logger.addLogAdapter(AndroidLogAdapter())

        // App logger

        Log.init()
    }
}
