package com.example.swissquotetest.data.network

import com.example.swissquotetest.data.models.remote.NetworkCardInfo
import com.example.swissquotetest.data.models.remote.NetworkTransactionInfo
import retrofit2.http.GET

interface TransactionsService {

    @GET("v1/c145c7af-b1ea-42f3-ae85-f6fabe6793c1")
    suspend fun getCards(): List<NetworkCardInfo>

    @GET("v1/22812b7a-d2dc-4377-bef9-843b674852c0")
    suspend fun getExecutedTransactions(): List<NetworkTransactionInfo>

    @GET("v1/b4dbd7be-2ea0-4fc2-837f-be4458f4dbc5")
    suspend fun getPendingTransactions(): List<NetworkTransactionInfo>
}