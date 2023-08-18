package com.koombea.data.character.datasource

import com.koombea.data.character.base.model.User
import com.koombea.data.character.base.OperationResult

interface AuthDataSource {
    suspend fun login(user: User): OperationResult<Boolean>
    suspend fun signUp(user: User): OperationResult<Boolean>
    suspend fun getUser(): OperationResult<User>
}