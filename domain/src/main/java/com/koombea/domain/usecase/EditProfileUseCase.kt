package com.koombea.domain.usecase

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.base.model.User
import com.koombea.data.character.repository.AuthRepository

class EditProfileUseCase(private val authRepository: AuthRepository) {
    suspend fun perform(user: User): OperationResult<Boolean> {
      return  when(val result =  authRepository.editUser(user)){
              is OperationResult.Success -> {
                  OperationResult.Success(result.data)
              }
              is OperationResult.Error -> {
                  OperationResult.Error(Exception())
              }
        }
    }
}