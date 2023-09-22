package com.koombea.data.character.repository

import com.koombea.data.character.base.model.User
import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.datasource.AuthDataSource

class AuthRepositoryImp(private val authDataSource: AuthDataSource):AuthRepository {

    override suspend fun login(user: User): OperationResult<Boolean> {
        return authDataSource.login(user)
    }

    override suspend fun signUp(user: User): OperationResult<Boolean> {
        return authDataSource.signUp(user)
    }

    override suspend fun getUser(): OperationResult<User> {
        return authDataSource.getUser()
    }

    override suspend fun signOut(user: User): OperationResult<Boolean> {
        return authDataSource.signOut(user)
    }

    override suspend fun editUser(user: User): OperationResult<Boolean> {
        return authDataSource.editProfile(user)
    }
}