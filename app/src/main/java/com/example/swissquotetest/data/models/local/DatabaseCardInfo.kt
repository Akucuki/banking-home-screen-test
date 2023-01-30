package com.example.swissquotetest.data.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.swissquotetest.data.models.domain.CardInfo

@Entity(tableName = "database_card_info")
data class DatabaseCardInfo(
    @PrimaryKey
    val id: Int,
    val type: String,
    val numbers: String,
    val amount: String,
    val currency: String
)

fun List<DatabaseCardInfo>.asDomainModel() = map {
    CardInfo(amount = it.amount, currency = it.currency, numbers = it.numbers)
}
