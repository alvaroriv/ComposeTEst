package com.koombea.data.character.datasource

import android.content.Context
import com.koombea.data.character.base.OperationResult
import com.scottyab.rootbeer.RootBeer

class RootCheckerDataSourceImpl(private val context: Context): RootCheckerDataSource {

    override suspend fun getRootStatus(): OperationResult<Boolean> {
        val rootBeer = RootBeer(context)
        return if (rootBeer.isRooted || rootBeer.isRootedWithBusyBoxCheck) {
            OperationResult.Success(true)
        } else {
            OperationResult.Error(Exception("Error"))
        }
    }
}