package com.example.swissquotetest.ui.features.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swissquotetest.data.models.domain.CardInfo
import com.example.swissquotetest.data.models.domain.TransactionInfo
import com.example.swissquotetest.data.repositories.TransactionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

private const val STATE_SHARING_TIMEOUT = 5000L

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TransactionsRepository
) : ViewModel() {

    val events = Channel<HomeEvent>(Channel.UNLIMITED)
    val cards: StateFlow<List<CardInfo>> =
        repository.cards.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(STATE_SHARING_TIMEOUT),
            listOf()
        )
    private val executedTransactions: Flow<List<TransactionInfo>> = repository.executedTransactions
    private val pendingTransactions: Flow<List<TransactionInfo>> = repository.pendingTransactions
    val transactions = combine(pendingTransactions, executedTransactions) { pending, executed ->
        (pending + executed).take(3)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(STATE_SHARING_TIMEOUT),
        listOf()
    )
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing //handle.getStateFlow(IS_REFRESHING, false)

    init { refreshData() }

    fun refreshData() {
        viewModelScope.launch {
            supervisorScope {
                _isRefreshing.value = true
                val refreshResultsDeferred = listOf(
                    async { repository.refreshCards() },
                    async { repository.refreshPendingTransactions() },
                    async { repository.refreshExecutedTransactions() }
                )
                try {
                    refreshResultsDeferred.awaitAll()
                } catch (e: Exception) {
                    Log.d("vitalik", "Error: ${e.message}")
                    events.trySend(HomeEvent.ShowErrorToast)
                }
                _isRefreshing.value = false
            }
        }
    }

    fun onLockCardClick() {
        events.trySend(HomeEvent.ShowCardLockedToast)
    }

    fun onSettingsClick() {
        events.trySend(HomeEvent.ShowSettingsToast)
    }
}