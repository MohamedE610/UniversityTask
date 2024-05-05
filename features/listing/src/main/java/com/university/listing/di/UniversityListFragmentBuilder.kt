package com.university.listing.di

import com.university.core.di.scope.FragmentScope
import com.university.listing.presentation.view.UniversityListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UniversityListFragmentBuilder {
    @FragmentScope
    @ContributesAndroidInjector(modules = [ListingModule::class, ListingViewModelModule::class])
    abstract fun bindUniversityListFragment(): UniversityListFragment

}