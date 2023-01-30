package com.example.swissquotetest.ui.features.home

sealed class HomeEvent {
    object ShowErrorToast : HomeEvent()
    object ShowCardLockedToast : HomeEvent()
    object ShowSettingsToast : HomeEvent()
}
