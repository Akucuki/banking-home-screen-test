package com.example.swissquotetest.ui.features.transactionDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swissquotetest.application.navigation.NAV_ARG_TRANSACTION_ID
import com.example.swissquotetest.data.models.domain.TransactionInfo
import com.example.swissquotetest.data.repositories.TransactionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TransactionDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: TransactionsRepository,
) : ViewModel() {

    private val transactionId = savedStateHandle.get<Int?>(NAV_ARG_TRANSACTION_ID)
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _isLoadingError = MutableStateFlow(false)
    val isLoadingError: StateFlow<Boolean> = _isLoadingError
    private val _transactionInfo = MutableStateFlow<TransactionInfo?>(null)
    val transactionInfo: StateFlow<TransactionInfo?> = _transactionInfo

    init { loadTransactionInfo() }

    private fun loadTransactionInfo() {
        if (transactionId == null) {
            _isLoadingError.value = true
            return
        }
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _transactionInfo.value = repository.getTransactionById(transactionId)
            } catch (e: Exception) {
                Log.d("vitalik", "Error: ${e.message}")
                _isLoadingError.value = true
            }
            _isLoading.value = false
        }
    }
}