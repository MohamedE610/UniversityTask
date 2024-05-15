package com.university.di.module

import android.app.Activity
import com.university.activity.AppNavigator
import com.university.activity.MainActivity
import com.university.core.di.scope.ActivityScope
import com.university.core.navigation.Navigator
import dagger.Binds
import dagger.Module

@Module
abstract class NavigatorModule {

    @ActivityScope
    @Binds
    abstract fun bindActivity(activity: MainActivity): Activity

    @ActivityScope
    @Binds
    abstract fun bindNavigator(appNavigator: AppNavigator): Navigator
}