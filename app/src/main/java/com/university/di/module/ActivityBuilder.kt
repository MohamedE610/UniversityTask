package com.university.di.module

import com.university.MainActivity
import com.university.core.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindLoadingActivity(): MainActivity
}