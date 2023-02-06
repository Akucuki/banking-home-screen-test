package com.example.swissquotetest.data.repositories

import com.example.swissquotetest.data.database.CardsInfoDao
import com.example.swissquotetest.data.database.TransactionInfoDao
import com.example.swissquotetest.data.models.domain.CardInfo
import com.example.swissquotetest.data.models.domain.TransactionInfo
import com.example.swissquotetest.data.models.domain.TransactionType
import com.example.swissquotetest.data.models.local.DatabaseTransactionInfo
import com.example.swissquotetest.data.models.local.asDomainModel
import com.example.swissquotetest.data.models.remote.asDatabaseModel
import com.example.swissquotetest.data.network.TransactionsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TransactionsRepository @Inject constructor(
    private val transactionsService: TransactionsService,
    private val cardsInfoDao: CardsInfoDao,
    private val transactionInfoDao: TransactionInfoDao
) {

    val cards: Flow<List<CardInfo>> = cardsInfoDao.loadAllCardsInfo().map { it.asDomainModel() }
    val executedTransactions: Flow<List<TransactionInfo>> =
        transactionInfoDao.loadDatabaseTransactionInfoWithType(TransactionType.EXECUTED)
            .map { it.asDomainModel() }
    val pendingTransactions: Flow<List<TransactionInfo>> =
        transactionInfoDao.loadDatabaseTransactionInfoWithType(TransactionType.PENDING)
            .map { it.asDomainModel() }

    suspend fun refreshCards() {
        withContext(Dispatchers.IO) {
            val cards = transactionsService.getCards()
            cardsInfoDao.insertAll(cards.asDatabaseModel())
        }
    }

    suspend fun refreshExecutedTransactions() {
        withContext(Dispatchers.IO) {
            val transactions = transactionsService.getExecutedTransactions()
            transactionInfoDao.insertAll(transactions.asDatabaseModel(TransactionType.EXECUTED))
        }
    }

    suspend fun refreshPendingTransactions() {
        withContext(Dispatchers.IO) {
            val transactions = transactionsService.getPendingTransactions()
            transactionInfoDao.insertAll(transactions.asDatabaseModel(TransactionType.PENDING))
        }
    }

    suspend fun getTransactionById(id: Int): TransactionInfo? {
        return withContext(Dispatchers.IO) {
            transactionInfoDao.loadDatabaseTransactionInfoWithId(id).asDomainModel()
        }
    }
}