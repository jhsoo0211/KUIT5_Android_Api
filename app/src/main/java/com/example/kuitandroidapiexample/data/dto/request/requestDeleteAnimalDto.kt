package com.example.kuitandroidapiexample.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DeleteDataDto(
    @SerialName("id")
    val id: Int
)