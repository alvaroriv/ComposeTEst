package com.koombea.data.character.datasource

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.base.model.Transaction

interface TransactionDataSource {
    suspend fun getTransaction(): OperationResult<List<Transaction>>
    suspend fun addTransaction(transaction: Transaction): OperationResult<Boolean>
}