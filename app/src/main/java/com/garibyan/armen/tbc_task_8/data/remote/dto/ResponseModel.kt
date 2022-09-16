package com.garibyan.armen.tbc_task_8.data.remote.dto

data class ResponseModel(
    val title: String,
    val cover: String?,
    val price: String?,
    val liked: Boolean?,
)