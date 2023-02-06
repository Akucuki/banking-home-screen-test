package com.example.swissquotetest.application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.swissquotetest.ui.features.home.HomeScreen
import com.example.swissquotetest.ui.features.transactionDetails.TransactionDetailsScreen

@Composable
fun PopulatedNavHost(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Destination.Home.route
    ) {
        composable(Destination.Home.route) {
            HomeScreen(
                onNavigateToTransactionDetails = { argument ->
                    val route = Destination.TransactionDetails.composeRouteWithArgumentsValues(argument)
                    navController.navigate(route)
                }
            )
        }
        composable(
            route = Destination.TransactionDetails.route,
            arguments = Destination.TransactionDetails.arguments
        ) {
            TransactionDetailsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}