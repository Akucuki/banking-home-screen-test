package com.example.swissquotetest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.swissquotetest.data.models.local.DatabaseCardInfo
import com.example.swissquotetest.data.models.local.DatabaseTransactionInfo

@Database(entities = [DatabaseCardInfo::class, DatabaseTransactionInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cardsInfoDao(): CardsInfoDao

    abstract fun transactionInfoDao(): TransactionInfoDao
}