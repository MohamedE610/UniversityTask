package com.university.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.university.R
import com.university.core.entity.University
import com.university.core.navigation.AppScreen
import com.university.details.UniversityDetailsFragment
import com.university.listing.presentation.view.UniversityListFragment


fun AppCompatActivity.navigateTo(appScreen: AppScreen) {
    supportFragmentManager.navigateTo(appScreen, ::navigateTo)
}

fun Fragment.navigateTo(appScreen: AppScreen) {
    activity?.supportFragmentManager?.navigateTo(appScreen, ::navigateTo)
}

private fun FragmentManager.navigateTo(appScreen: AppScreen, navigateTo: (AppScreen) -> Unit) {
    when (appScreen) {
        is AppScreen.Listing -> navigateToUniversityListingScreen(navigateTo)
        is AppScreen.Details -> navigateToUniversityDetailsScreen(appScreen.university, navigateTo)
    }
}

private fun FragmentManager.navigateToUniversityListingScreen(navigateTo: (AppScreen) -> Unit) {
    beginTransaction()
        .replace(
            R.id.fl_screen_container,
            UniversityListFragment(navigateTo = navigateTo),
            UniversityListFragment::class.simpleName
        ).commit()
}

private fun FragmentManager.navigateToUniversityDetailsScreen(
    university: University,
    navigateTo: (AppScreen) -> Unit
) {
    val fragment = UniversityDetailsFragment(navigateTo = navigateTo).apply {
        arguments = UniversityDetailsFragment.buildBundle(university)
    }
    beginTransaction()
        .add(
            R.id.fl_screen_container,
            fragment,
            UniversityDetailsFragment::class.simpleName
        )
        .addToBackStack(UniversityDetailsFragment::class.simpleName)
        .commit()
}
