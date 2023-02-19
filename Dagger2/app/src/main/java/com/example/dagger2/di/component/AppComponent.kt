package com.example.dagger2.di.component

import com.example.dagger2.MainActivity
import com.example.dagger2.di.module.DatabaseModule
import com.example.dagger2.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}