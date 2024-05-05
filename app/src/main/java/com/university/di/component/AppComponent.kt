package com.university.di.component

import android.app.Application
import com.university.application.App
import com.university.core.di.module.AppModule
import com.university.core.di.module.DispatchersModule
import com.university.core.di.module.LocalStorageModule
import com.university.core.di.module.NetworkModule
import com.university.di.module.ActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        AppModule::class,
        NetworkModule::class,
        LocalStorageModule::class,
        DispatchersModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    override fun inject(instance: App?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
