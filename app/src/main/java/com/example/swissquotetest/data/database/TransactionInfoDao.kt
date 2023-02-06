package com.example.swissquotetest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swissquotetest.data.models.domain.TransactionType
import com.example.swissquotetest.data.models.local.DatabaseTransactionInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionInfoDao {

    @Query("SELECT * FROM database_transaction_info")
    fun loadAllDatabaseTransactionInfo(): Flow<List<DatabaseTransactionInfo>>

    @Query("SELECT * FROM database_transaction_info WHERE type=:transactionType")
    fun loadDatabaseTransactionInfoWithType(transactionType: TransactionType): Flow<List<DatabaseTransactionInfo>>

    @Query("SELECT * FROM database_transaction_info WHERE id=:id LIMIT 1")
    fun loadDatabaseTransactionInfoWithId(id: Int): DatabaseTransactionInfo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(transactionsInfo: List<DatabaseTransactionInfo>)
}