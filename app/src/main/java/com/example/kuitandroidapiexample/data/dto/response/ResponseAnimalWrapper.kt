package com.example.kuitandroidapiexample.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseAnimalWrapper(
    val data: ResponseAnimalDto
)
