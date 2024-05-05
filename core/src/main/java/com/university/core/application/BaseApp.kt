package com.university.core.application

import android.app.Application

open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: BaseApp
            private set
    }
}