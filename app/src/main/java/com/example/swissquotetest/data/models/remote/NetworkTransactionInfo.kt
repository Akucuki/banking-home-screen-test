package com.example.swissquotetest.data.models.remote

import com.example.swissquotetest.data.models.domain.TransactionType
import com.example.swissquotetest.data.models.local.DatabaseTransactionInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkTransactionInfo(
    val id: Int,
    @Json(name = "merchand")
    val merchant: String,
    val category: String,
    val amount: String,
    val currency: String,
    val timestamp: Int
)

fun List<NetworkTransactionInfo>.asDatabaseModel(transactionType: TransactionType) = map {
    DatabaseTransactionInfo(
        id = it.id,
        merchant = it.merchant,
        category = it.category,
        amount = it.amount,
        currency = it.currency,
        timestamp = it.timestamp,
        type = transactionType
    )
}
