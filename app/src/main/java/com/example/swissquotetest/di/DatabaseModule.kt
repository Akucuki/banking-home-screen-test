package com.example.swissquotetest.di

import android.content.Context
import androidx.room.Room
import com.example.swissquotetest.data.database.AppDatabase
import com.example.swissquotetest.data.database.CardsInfoDao
import com.example.swissquotetest.data.database.TransactionInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app-database").build()
    }

    @Singleton
    @Provides
    fun provideCardsInfoDao(database: AppDatabase): CardsInfoDao = database.cardsInfoDao()

    @Singleton
    @Provides
    fun provideTransactionInfoDao(database: AppDatabase): TransactionInfoDao = database.transactionInfoDao()
}