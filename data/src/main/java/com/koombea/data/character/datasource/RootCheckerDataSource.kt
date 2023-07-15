package com.koombea.data.character.datasource

import com.koombea.data.character.base.OperationResult

interface RootCheckerDataSource {
    suspend fun getRootStatus(): OperationResult<Boolean>
}