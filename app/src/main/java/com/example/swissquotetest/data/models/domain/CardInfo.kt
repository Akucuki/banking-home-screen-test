package com.example.swissquotetest.data.models.domain

data class CardInfo(
    val numbers: String,
    val amount: String,
    val currency: String
) {
    val fullBalanceValue = "$amount $currency"
}
