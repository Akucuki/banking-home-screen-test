package com.example.swissquotetest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swissquotetest.data.models.local.DatabaseCardInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface CardsInfoDao {

    @Query("SELECT * FROM database_card_info")
    fun loadAllCardsInfo(): Flow<List<DatabaseCardInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cardsInfo: List<DatabaseCardInfo>)
}