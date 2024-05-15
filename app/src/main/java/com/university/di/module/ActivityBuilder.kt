package com.university.di.module

import com.university.activity.AppNavigator
import com.university.activity.MainActivity
import com.university.core.di.scope.ActivityScope
import com.university.core.navigation.Navigator
import com.university.listing.di.UniversityListFragmentBuilder
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [UniversityListFragmentBuilder::class, NavigatorModule::class])
    abstract fun bindLoadingActivity(): MainActivity
}