package com.university.core.di.module

import com.university.core.di.qualifier.Dispatcher
import com.university.core.di.qualifier.DispatcherKey
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class DispatchersModule {
    @Provides
    @Dispatcher(DispatcherKey.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(DispatcherKey.COMPUTATION)
    fun providesComputationDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Dispatcher(DispatcherKey.MAIN)
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}