package com.mehdizadeh.catfeed.navigation.provider

import com.mehdizadeh.catfeed.navigation.screen.AppScreens
import com.mehdizadeh.catfeed.navigation.screen.Screen

interface NavigationStartDestinationProvider {
    fun getStartDestination(): Screen
}

object DefaultStartDestinationProvider : NavigationStartDestinationProvider {
    override fun getStartDestination(): Screen {
        return AppScreens.Splash
    }
}