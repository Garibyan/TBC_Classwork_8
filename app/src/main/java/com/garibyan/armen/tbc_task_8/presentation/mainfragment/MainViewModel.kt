package com.garibyan.armen.tbc_task_8.presentation.mainfragment

import com.garibyan.armen.tbc_task_8.common.base.BaseViewModel
import com.garibyan.armen.tbc_task_8.common.utils.ViewState
import com.garibyan.armen.tbc_task_8.data.remote.dto.ResponseModel
import com.garibyan.armen.tbc_task_8.domain.usecase.GetAllItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getAllItemsUseCase: GetAllItemsUseCase) : BaseViewModel() {

    private val _itemsState = MutableStateFlow(ViewState<List<ResponseModel>>())
    val itemsState = _itemsState.asStateFlow()

    suspend fun getAllCourses() {
        responseHandler(getAllItemsUseCase(), _itemsState.value).collect{
            _itemsState.value = it
        }
    }
}