package com.garibyan.armen.tbc_task_8.data.remote.repository

import com.garibyan.armen.tbc_task_8.common.base.BaseRepository
import com.garibyan.armen.tbc_task_8.data.remote.ApiService
import com.garibyan.armen.tbc_task_8.common.utils.Resource
import com.garibyan.armen.tbc_task_8.data.remote.dto.ResponseModel
import com.garibyan.armen.tbc_task_8.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService): MainRepository, BaseRepository() {
    override suspend fun getAllItems(): Flow<Resource<List<ResponseModel>>> {
        return saveApiCall { apiService.getAllItems() }
    }
}