package com.koombea.data.character.datasource

import android.content.Context
import com.couchbase.lite.Expression
import com.koombea.couchbasewrapper.database.CouchbaseDatabase
import com.koombea.couchbasewrapper.database.CouchbaseDocument
import com.koombea.data.character.base.model.User
import com.koombea.data.character.base.OperationResult
import com.scottyab.rootbeer.RootBeer

class AuthDataSourceImpl(private val context: Context,private val couchbaseDatabase: CouchbaseDatabase): AuthDataSource {

    override suspend fun login(email: String, password: String): OperationResult<Boolean> {
        addTestUser()

        val expression = Expression.property("attributes.email").equalTo(Expression.string(email))
            .and(Expression.property("attributes.password").equalTo(Expression.string(password)))
        val user = couchbaseDatabase.fetchAll<User>(whereExpression = expression)
        return if (user.isNotEmpty()) {
            OperationResult.Success(true)
        } else {
            OperationResult.Error(Exception("Error"))
        }
    }

    override suspend fun signUp(user: User): OperationResult<Boolean> {
        return try {
            val document = CouchbaseDocument(id = System.currentTimeMillis().toString(), attributes = user)
            couchbaseDatabase.save(document)
            OperationResult.Success(true)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    private fun addTestUser(){
        val user2 = User(email = "a@a.com", password ="111")
        val document = CouchbaseDocument(id = "1", attributes = user2)
        couchbaseDatabase.save(document)
    }
}