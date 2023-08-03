package com.koombea.data.character.repository

import com.koombea.data.character.base.model.User
import com.koombea.data.character.base.OperationResult

interface AuthRepository {
     suspend fun login(email: String, password: String): OperationResult<Boolean>
     suspend fun signUp(user: User): OperationResult<Boolean>
}