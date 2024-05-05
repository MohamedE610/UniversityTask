package com.university.di.module

import com.university.activity.MainActivity
import com.university.core.di.scope.ActivityScope
import com.university.listing.di.UniversityListFragmentBuilder
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [UniversityListFragmentBuilder::class])
    abstract fun bindLoadingActivity(): MainActivity
}