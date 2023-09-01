package com.koombea.data.character.repository

import com.koombea.data.character.base.model.User
import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.base.model.Transaction
import com.koombea.data.character.datasource.AuthDataSource
import com.koombea.data.character.datasource.TransactionDataSource

class TransactionRepositoryImp(private val transactionDataSource: TransactionDataSource):TransactionRepository {

    override suspend fun getTransaction(): OperationResult<List<Transaction>> {
        return transactionDataSource.getTransaction()
    }

    override suspend fun addTransaction(transaction: Transaction): OperationResult<Boolean> {
        return transactionDataSource.addTransaction(transaction)
    }
}