package com.example.swissquotetest.data.models.domain

import com.example.swissquotetest.R

enum class TransactionType {
    EXECUTED, PENDING;

    fun toStringId() = when (this) {
        EXECUTED -> R.string.executed
        else -> R.string.pending
    }
}

data class TransactionInfo(
    val id: Int,
    val merchant: String,
    val category: String,
    val amount: String,
    val currency: String,
    val timestamp: Int,
    val type: TransactionType
) {
    val fullAmountValue = "$amount $currency"
}
