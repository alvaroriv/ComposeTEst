package com.koombea.data.character.datasource

import android.content.Context
import com.couchbase.lite.Expression
import com.koombea.couchbasewrapper.database.CouchbaseDatabase
import com.koombea.couchbasewrapper.database.CouchbaseDocument
import com.koombea.data.character.base.model.User
import com.koombea.data.character.base.OperationResult
import com.scottyab.rootbeer.RootBeer

class AuthDataSourceImpl(private val couchbaseDatabase: CouchbaseDatabase): AuthDataSource {

    override suspend fun login(user: User): OperationResult<Boolean> {
        addTestUser()

        val expression = Expression.property("attributes.email").equalTo(Expression.string(user.email))
            .and(Expression.property("attributes.password").equalTo(Expression.string(user.password)))
        val user = couchbaseDatabase.fetchAll<User>(whereExpression = expression)
        return if (user.isNotEmpty()) {
            OperationResult.Success(true)
        } else {
            OperationResult.Error(Exception("Error"))
        }
    }

    override suspend fun signUp(user: User): OperationResult<Boolean> {
        return try {
            val document = CouchbaseDocument(id = "2", attributes = user)
            couchbaseDatabase.save(document)
            OperationResult.Success(true)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun getUser(): OperationResult<User> {
        //val expression = Expression.property("attributes.email").equalTo(Expression.string(email))
        val user = couchbaseDatabase.fetch<User>("2")
        return if (user!=null) {
            OperationResult.Success(user)
        } else {
            OperationResult.Error(Exception("Error"))
        }
    }

    private fun addTestUser(){
        val user2 = User(email = "a@a.com", password ="111", name = "alvaro rivera")
        val document = CouchbaseDocument(id = "2", attributes = user2)
        couchbaseDatabase.save(document)
    }
}