package com.university.core.di.module

import android.app.Application
import android.content.Context
import com.university.core.di.qualifier.ContextKey
import com.university.core.di.qualifier.ContextQualifier
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {
    @Provides
    @Singleton
    fun providesApplication(application: Application): Application {
        return application
    }

    @Provides
    @Singleton
    @ContextQualifier(ContextKey.APP)
    fun providesApplicationContext(application: Application): Context {
        return application.applicationContext
    }
}