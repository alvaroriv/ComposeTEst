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
        val user2 = User(email = "aaa", password ="111")
        val document = CouchbaseDocument(id = "1", attributes = user2)
        couchbaseDatabase.save(document)

        val expression = Expression.property("attributes.email").equalTo(Expression.string(email))
            .and(Expression.property("attributes.password").equalTo(Expression.string(password)))
        val user = couchbaseDatabase.fetchAll<User>(whereExpression = expression)
        // val documents = couchbaseDatabase.fetch<User>("1")
        return if (user.isNotEmpty()) {
            OperationResult.Success(true)
        } else {
            OperationResult.Error(Exception("Error"))
        }
    }

    override suspend fun signUp(user: User): OperationResult<Boolean> {

        return if (user!=null) {
            OperationResult.Success(true)
        } else {
            OperationResult.Error(Exception("Error"))
        }
    }
}