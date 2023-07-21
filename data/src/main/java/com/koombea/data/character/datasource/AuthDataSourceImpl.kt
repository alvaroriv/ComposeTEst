package com.koombea.data.character.datasource

import android.content.Context
import com.koombea.data.character.base.OperationResult
import com.scottyab.rootbeer.RootBeer

class AuthDataSourceImpl(private val context: Context): AuthDataSource {

    override suspend fun login(email: String, password: String): OperationResult<Boolean> {

        return if (email == "aaa" && password == "1234") {
            OperationResult.Success(true)
        } else {
            OperationResult.Error(Exception("Error"))
        }
    }
}