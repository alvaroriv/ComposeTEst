package com.koombea.data.character.datasource

import android.content.Context
import com.couchbase.lite.Expression
import com.koombea.couchbasewrapper.database.CouchbaseDatabase
import com.koombea.couchbasewrapper.database.CouchbaseDocument
import com.koombea.data.character.base.model.User
import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.base.model.Transaction
import com.scottyab.rootbeer.RootBeer

class TransactionDataSourceImpl(private val couchbaseDatabase: CouchbaseDatabase): TransactionDataSource {

    override suspend fun getTransaction(): OperationResult<List<Transaction>> {
        val expression = Expression.property("attributes.id").equalTo(Expression.string("1"))
        val transactions = couchbaseDatabase.fetchAll<Transaction>(whereExpression = expression)
        return if (transactions.isNotEmpty()) {
            OperationResult.Success(transactions)
        } else {
            OperationResult.Error(Exception("Error"))
        }
    }

    override suspend fun addTransaction(transaction: Transaction): OperationResult<Boolean> {
        return try {
            val document = CouchbaseDocument(id = System.currentTimeMillis().toString(), attributes = transaction)
            couchbaseDatabase.save(document)
            OperationResult.Success(true)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }
}