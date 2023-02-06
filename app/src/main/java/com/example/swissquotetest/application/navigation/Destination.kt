package com.example.swissquotetest.application.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

private const val ROUTE_SEPARATOR = "/"

const val NAV_ARG_TRANSACTION_ID = "transactionId"

sealed class Destination(routeBase: String, val arguments: List<NamedNavArgument> = listOf()) {

    val route =
        "$routeBase${
            arguments.joinToString(
                prefix = ROUTE_SEPARATOR,
                separator = ROUTE_SEPARATOR
            ) {
                "{${it.name}}"
            }
        }"

    fun composeRouteWithArgumentsValues(vararg argumentsValues: Any): String {
        require(argumentsValues.size == arguments.size) {
            "Wrong arguments supplied for route: $route"
        }
        var result = route
        for (i in arguments.indices) {
            val argumentName = arguments[i].name
            val argumentValue = argumentsValues[i]
            result = result.replace(
                oldValue = "{$argumentName}",
                newValue = argumentValue.toString()
            )
        }
        return result
    }


    object Home : Destination("home")
    object TransactionDetails : Destination(
        routeBase = "transactionDetails",
        arguments = listOf(
            navArgument(NAV_ARG_TRANSACTION_ID) { type = NavType.IntType }
        )
    )
}
