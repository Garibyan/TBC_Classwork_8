package com.garibyan.armen.tbc_task_8.domain.usecase

import com.garibyan.armen.tbc_task_8.data.remote.repository.ApiRepository
import javax.inject.Inject

class GetAllItemsUseCase @Inject constructor(private val apiRepository: ApiRepository) {

    suspend operator fun invoke() = apiRepository.getAllItems()
}
