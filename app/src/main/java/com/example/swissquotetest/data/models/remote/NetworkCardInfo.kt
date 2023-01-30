package com.example.swissquotetest.data.models.remote

import com.example.swissquotetest.data.models.local.DatabaseCardInfo
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkCardInfo(
    val id: Int,
    val type: String,
    val numbers: String,
    val amount: String,
    val currency: String
)

fun List<NetworkCardInfo>.asDatabaseModel() = map {
    DatabaseCardInfo(
        id = it.id,
        type = it.type,
        numbers = it.numbers,
        amount = it.amount,
        currency = it.currency
    )
}
