package com.garibyan.armen.tbc_task_8.common.utils

data class ViewState<T>(
    val data: T? = null,
    val loading: Boolean = false,
    val error: String = ""
)