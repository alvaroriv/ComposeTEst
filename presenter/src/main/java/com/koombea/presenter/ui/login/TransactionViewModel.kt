package com.koombea.presenter.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.base.model.Transaction
import com.koombea.domain.usecase.AddTransactionUseCase
import com.koombea.domain.usecase.GetTransactionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransactionViewModel(private val getTransactionsUseCase: GetTransactionsUseCase, private val  addTransactionUseCase: AddTransactionUseCase) : ViewModel() {

    private val _state = MutableStateFlow(AddTransactionState())

    val state: StateFlow<AddTransactionState>
        get() = _state

    fun getTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            getTransactionsUseCase.perform().let { result ->
                when (result) {
                    is OperationResult.Success -> {
                        _state.value = _state.value.copy(transactions = result.data!!)
                    }
                    is OperationResult.Error -> {
                        _state.value = _state.value.copy(error = result.exception?.message )
                    }

                }
            }
        }
    }

    fun addTransaction(transaction: Transaction) {
        _state.value = _state.value.copy(isAdded = -1)
        viewModelScope.launch(Dispatchers.IO) {
            addTransactionUseCase.perform(transaction).let { result ->
                when (result) {
                    is OperationResult.Success -> {
                        _state.value = _state.value.copy(isAdded = 1)
                    }
                    is OperationResult.Error -> {
                        _state.value = _state.value.copy(error = result.exception?.message )
                    }

                }
            }
        }
    }
}

data class AddTransactionState(
    val error: String? = "",
    val isAdded: Int = -1,
    val transactions: List<Transaction> = listOf()
)