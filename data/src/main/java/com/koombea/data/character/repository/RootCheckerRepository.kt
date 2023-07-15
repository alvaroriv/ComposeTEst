package com.koombea.data.character.repository

import com.koombea.data.character.base.OperationResult

interface RootCheckerRepository {
     suspend fun getRootStatus(): OperationResult<Boolean>
}