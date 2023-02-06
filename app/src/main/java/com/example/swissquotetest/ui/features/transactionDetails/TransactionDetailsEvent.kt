package com.example.swissquotetest.ui.features.transactionDetails

import com.example.swissquotetest.ui.features.home.HomeEvent

sealed class TransactionDetailsEvent {
    object NavigateBack : TransactionDetailsEvent()
}
