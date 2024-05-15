package com.university.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.university.core.navigation.AppScreen
import com.university.core.navigation.Navigator
import javax.inject.Inject

class AppNavigator @Inject constructor(private val activity: Activity) : Navigator {
    override fun navigateTo(screen: AppScreen) {
        (activity as? AppCompatActivity)?.navigateTo(appScreen = screen)
    }
}