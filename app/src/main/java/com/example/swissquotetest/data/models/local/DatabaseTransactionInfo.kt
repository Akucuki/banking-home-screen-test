package com.example.swissquotetest.data.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.swissquotetest.data.models.domain.TransactionInfo
import com.example.swissquotetest.data.models.domain.TransactionType

@Entity(tableName = "database_transaction_info")
data class DatabaseTransactionInfo(
    @PrimaryKey
    val id: Int,
    val merchant: String,
    val category: String,
    val amount: String,
    val currency: String,
    val timestamp: Int,
    val type: TransactionType
) {
    fun asDomainModel() = TransactionInfo(
        id = id,
        merchant = merchant,
        category = category,
        amount = amount,
        currency = currency,
        timestamp = timestamp,
        type = type
    )
}

fun List<DatabaseTransactionInfo>.asDomainModel() = map { it.asDomainModel() }
