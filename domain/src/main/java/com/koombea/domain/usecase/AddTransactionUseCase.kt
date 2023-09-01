package com.koombea.domain.usecase

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.base.model.Transaction
import com.koombea.data.character.repository.TransactionRepository

class AddTransactionUseCase(private val transactionRepository: TransactionRepository) {
    suspend fun perform(transaction: Transaction): OperationResult<Boolean> {
        return  when(val result =  transactionRepository.addTransaction(transaction)){
            is OperationResult.Success -> {
                OperationResult.Success(result.data)
            }
            is OperationResult.Error -> {
                OperationResult.Error(Exception())
            }
        }
    }
}