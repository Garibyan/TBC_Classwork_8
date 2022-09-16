package com.garibyan.armen.tbc_task_8.common.utils

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    object Loading : Resource<Nothing>()
    data class Error(val errorMsg: String) : Resource<Nothing>()
}