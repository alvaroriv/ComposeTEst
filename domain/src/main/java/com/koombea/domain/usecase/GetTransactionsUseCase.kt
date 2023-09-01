package com.koombea.domain.usecase

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.base.model.Transaction
import com.koombea.data.character.repository.TransactionRepository

class GetTransactionsUseCase(private val transactionRepository: TransactionRepository) {
    suspend fun perform(): OperationResult<List<Transaction>> {
      return  when(val result =  transactionRepository.getTransaction()){
              is OperationResult.Success -> {
                  OperationResult.Success(result.data)
              }
              is OperationResult.Error -> {
                  OperationResult.Error(Exception())
              }
        }
    }
}