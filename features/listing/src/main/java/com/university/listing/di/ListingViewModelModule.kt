package com.university.listing.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.university.core.di.module.ViewModelFactory
import com.university.core.di.module.ViewModelKey
import com.university.listing.presentation.viewmodel.UniversityListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ListingViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UniversityListViewModel::class)
    abstract fun bindViewModel(mainViewModel: UniversityListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}