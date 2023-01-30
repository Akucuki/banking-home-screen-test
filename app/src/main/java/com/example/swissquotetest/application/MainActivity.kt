package com.example.swissquotetest.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swissquotetest.ui.features.home.HomeScreen
import com.example.swissquotetest.ui.theme.SwissquoteTestTheme
import com.example.swissquotetest.utils.Destination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            SwissquoteTestTheme {
                NavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    startDestination = Destination.Home.route
                ) {
                    composable(Destination.Home.route) {
                        HomeScreen()
                    }
                }
            }
        }
    }
}
