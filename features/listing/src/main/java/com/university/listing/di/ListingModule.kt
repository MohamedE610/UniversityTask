package com.university.listing.di

import com.university.core.datasource.local.room.dao.UniversityDao
import com.university.core.datasource.local.room.database.UniversityDatabase
import com.university.core.di.scope.FragmentScope
import com.university.listing.data.UniversityRemoteDS
import com.university.listing.data.UniversityRepositoryImpl
import com.university.listing.domain.UniversityRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [ListingModule.BindsModule::class])
class ListingModule {

    @FragmentScope
    @Provides
    fun providesRemoteDataSource(retrofit: Retrofit): UniversityRemoteDS {
        return retrofit.create(UniversityRemoteDS::class.java)
    }

    @FragmentScope
    @Provides
    fun providesLocalDataSource(database: UniversityDatabase): UniversityDao {
        return database.universityDao()
    }

    @Module
    interface BindsModule {
        @FragmentScope
        @Binds
        fun bindRepository(repository: UniversityRepositoryImpl): UniversityRepository
    }

}