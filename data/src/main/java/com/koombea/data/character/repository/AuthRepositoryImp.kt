package com.koombea.data.character.repository

import com.koombea.data.character.base.model.User
import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.datasource.AuthDataSource

class AuthRepositoryImp(private val authDataSource: AuthDataSource):AuthRepository {

    override suspend fun login(email: String, password: String): OperationResult<Boolean> {
        return authDataSource.login(email, password)
    }

    override suspend fun signUp(user: User): OperationResult<Boolean> {
        return authDataSource.signUp(user)
    }

}