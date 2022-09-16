package com.garibyan.armen.tbc_task_8.domain.repository

import com.garibyan.armen.tbc_task_8.common.utils.Resource
import com.garibyan.armen.tbc_task_8.data.remote.dto.ResponseModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getAllItems(): Flow<Resource<List<ResponseModel>>>
}