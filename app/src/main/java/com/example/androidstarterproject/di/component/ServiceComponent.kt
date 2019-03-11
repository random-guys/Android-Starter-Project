package com.example.androidstarterproject.di.component

import com.example.androidstarterproject.di.annotations.PerService
import com.example.androidstarterproject.di.module.ServiceModule
import dagger.Component

@PerService
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ServiceModule::class)])
interface ServiceComponent