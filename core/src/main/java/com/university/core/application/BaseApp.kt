package com.university.core.application

import dagger.android.DaggerApplication

abstract class BaseApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: BaseApp? = null
            private set
    }
}