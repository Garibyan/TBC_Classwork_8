package com.garibyan.armen.tbc_task_8.common.base

import androidx.lifecycle.ViewModel
import com.garibyan.armen.tbc_task_8.common.utils.ViewState
import com.garibyan.armen.tbc_task_8.common.utils.Resource
import kotlinx.coroutines.flow.Flow


abstract class BaseViewModel : ViewModel() {

    suspend fun <T> responseHandler(flow: Flow<Resource<T>>, state: ViewState<T>) =
        kotlinx.coroutines.flow.flow {
            var newState = state
            flow.collect {
                when (it) {
                    is Resource.Success -> {
                        newState = state.copy(data = it.value, loading = false, error = "")
                        emit(newState)
                    }
                    is Resource.Loading -> {
                        newState = state.copy(loading = true)
                        emit(newState)
                    }
                    is Resource.Error -> {
                        newState = state.copy(loading = false, data = null, error = it.errorMsg)
                        emit(newState)
                    }
                }
            }
        }

}