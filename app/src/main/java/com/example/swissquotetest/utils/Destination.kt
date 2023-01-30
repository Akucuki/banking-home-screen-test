package com.example.swissquotetest.utils

sealed class Destination(val route: String) {
    object Home : Destination("home")
}
