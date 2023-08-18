package com.koombea.domain.usecase

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.base.model.User
import com.koombea.data.character.repository.AuthRepository

class GetUserUseCase(private val authRepository: AuthRepository) {
    suspend fun perform(): OperationResult<User> {
      return  when(val result =  authRepository.getUser()){
              is OperationResult.Success -> {
                  OperationResult.Success(result.data)
              }
              is OperationResult.Error -> {
                  OperationResult.Error(Exception())
              }
        }
    }
}