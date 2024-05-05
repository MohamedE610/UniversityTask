package com.university.application

import com.university.core.application.BaseApp
import com.university.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : BaseApp() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
            .also { it.inject(this) }
    }
}