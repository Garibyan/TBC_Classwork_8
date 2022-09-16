package com.garibyan.armen.tbc_task_8.data.remote

import com.garibyan.armen.tbc_task_8.data.remote.dto.ResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("05d71804-4628-4269-ac03-f86e9960a0bb")
    suspend fun getAllItems(): Response<List<ResponseModel>>
}