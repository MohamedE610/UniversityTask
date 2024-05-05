package com.university.core.di.module

import android.content.Context
import com.university.core.datasource.local.room.database.UniversityDatabase
import com.university.core.di.qualifier.ContextKey
import com.university.core.di.qualifier.ContextQualifier
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalStorageModule {

    @Provides
    @Singleton
    fun provideUniversityDatabaseDB(@ContextQualifier(ContextKey.APP) context: Context): UniversityDatabase {
        return UniversityDatabase.getInstance(context)
    }
}