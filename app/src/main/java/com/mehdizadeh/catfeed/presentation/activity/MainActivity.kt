package com.mehdizadeh.catfeed.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mehdizadeh.catfeed.navigation.manager.LocalNavigationManager
import com.mehdizadeh.catfeed.navigation.manager.NavigationManager
import com.mehdizadeh.catfeed.navigation.screen.appScreens
import com.mehdizadeh.catfeed.ui.theme.CatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewState by viewModel.viewState.collectAsState()
            CatTheme(themeMode = viewState.themeMode, content = {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavHost(
                        navController = navController,
                        innerPadding = innerPadding
                    )
                }
            })
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    val navigationManager = remember {
        NavigationManager(
            navController = navController,
        )
    }

    CompositionLocalProvider(LocalNavigationManager provides navigationManager) {
        NavHost(
            navController = navigationManager.navController,
            startDestination = navigationManager.getStartDestination().route,
        ) {
            appScreens()
        }
    }
}