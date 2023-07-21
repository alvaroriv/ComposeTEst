package com.koombea.domain.usecase

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend fun perform(email: String, password: String): OperationResult<Boolean> {
      return  when(val result =  authRepository.login(email,password)){
              is OperationResult.Success -> {
                  OperationResult.Success(result.data)
              }
              is OperationResult.Error -> {
                  OperationResult.Error(Exception())
              }
        }
    }
}