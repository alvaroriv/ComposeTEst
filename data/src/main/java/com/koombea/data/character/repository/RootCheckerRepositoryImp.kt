package com.koombea.data.character.repository

import com.koombea.data.character.base.OperationResult
import com.koombea.data.character.datasource.RootCheckerDataSource

class RootCheckerRepositoryImp(private val rootCheckerDataSource: RootCheckerDataSource):RootCheckerRepository {

    override suspend fun getRootStatus(): OperationResult<Boolean> {
        return rootCheckerDataSource.getRootStatus()
    }

}