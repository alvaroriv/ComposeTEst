package com.koombea.domain.usecase

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.repository.RootCheckerRepository

class GetRootStatusUseCase(private val rootCheckerRepository: RootCheckerRepository) {
    suspend fun perform(): OperationResult<Boolean> {
      return  when(val result =  rootCheckerRepository.getRootStatus()){
              is OperationResult.Success -> {
                  OperationResult.Success(result.data)
              }
              is OperationResult.Error -> {
                  OperationResult.Error(Exception())
              }
        }
    }
}