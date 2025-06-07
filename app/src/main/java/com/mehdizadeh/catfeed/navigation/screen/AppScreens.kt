package com.mehdizadeh.catfeed.navigation.screen

sealed class AppScreens(route :String,arguments: Map<String, String>): Screen(baseRoute = route,arguments =arguments){
    object Splash : AppScreens("splash", emptyMap())
}