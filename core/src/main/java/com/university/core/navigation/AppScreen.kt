package com.university.core.navigation

import com.university.core.entity.University

sealed class AppScreen(val className: String) {
    data object Listing :
        AppScreen("com.university.listing.presentation.view.UniversityListFragment")

    data class Details(val university: University) :
        AppScreen("com.university.details.UniversityDetailsFragment")
}