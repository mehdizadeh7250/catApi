package com.mehdizadeh.catfeed.navigation.screen

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mehdizadeh.catfeed.presentation.screen.details.DetailsScreen
import com.mehdizadeh.catfeed.presentation.screen.home.HomeScreen
import com.mehdizadeh.catfeed.presentation.screen.home.viewModel.HomeViewModel

sealed class AppScreens(route :String,arguments: Map<String, String> = emptyMap()): Screen(baseRoute = route,arguments =arguments){
    object AppScreenGraph : AppScreens("app_screen_graph")
    object HomeScreen : AppScreens("home_screen")
    object DetailScreen : AppScreens("detail_screen")
}

fun NavGraphBuilder.appScreens(
) {
    navigation(
        route = AppScreens.AppScreenGraph.route,
        startDestination =AppScreens.HomeScreen.route
    ) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(viewModel = hiltViewModel<HomeViewModel>())
        }
        composable(route = AppScreens.DetailScreen.route) {
            DetailsScreen()
        }
    }

}