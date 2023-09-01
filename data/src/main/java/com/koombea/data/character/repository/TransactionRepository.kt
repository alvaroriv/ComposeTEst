package com.koombea.data.character.repository

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.base.model.Transaction

interface TransactionRepository {
     suspend fun getTransaction(): OperationResult<List<Transaction>>
     suspend fun addTransaction(transaction: Transaction): OperationResult<Boolean>
}