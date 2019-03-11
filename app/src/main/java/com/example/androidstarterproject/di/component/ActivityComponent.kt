package com.example.androidstarterproject.di.component

import dagger.Component
import com.example.androidstarterproject.di.annotations.PerActivity
import com.example.androidstarterproject.di.module.ActivityModule


/**
 * Created by rtukpe on 13/03/2018.
 */

@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {

    // Activities
  
}
