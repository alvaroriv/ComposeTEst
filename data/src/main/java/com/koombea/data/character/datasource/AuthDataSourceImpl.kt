package com.koombea.data.character.datasource

import com.couchbase.lite.Expression
import com.koombea.couchbasewrapper.database.CouchbaseDatabase
import com.koombea.couchbasewrapper.database.CouchbaseDocument
import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.base.model.User

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
            val document = CouchbaseDocument(id = "1", attributes = user)
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

    override suspend fun signOut(user: User): OperationResult<Boolean> {
        return try {
            val expression = Expression.property("attributes.email").equalTo(Expression.string(user.email))
                .and(Expression.property("attributes.password").equalTo(Expression.string(user.password)))
            couchbaseDatabase.deleteAll(whereExpression = expression)
            OperationResult.Success(true)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    override suspend fun editProfile(user: User?): OperationResult<Boolean> {
        return try {
            val document = CouchbaseDocument(id = "2", attributes = user)
            couchbaseDatabase.save(document)
            OperationResult.Success(true)
        } catch (e: Exception) {
            OperationResult.Error(e)
        }
    }

    private fun addTestUser(){
        val user2 = User(email = "aaa", password ="111", name = "alvaro rivera")
        val document = CouchbaseDocument(id = "2", attributes = user2)
        couchbaseDatabase.save(document)
    }
}