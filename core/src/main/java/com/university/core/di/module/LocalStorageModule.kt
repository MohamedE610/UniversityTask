package com.university.core.di.module

import android.content.Context
import com.university.core.datasource.local.room.database.UniversityDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalStorageModule {

    @Provides
    @Singleton
    fun providePremierLeagueDB(context: Context): UniversityDatabase {
        return UniversityDatabase.getInstance(context)
    }
}