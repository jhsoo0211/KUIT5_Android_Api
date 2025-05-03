package com.example.kuitandroidapiexample.data.dto.response

import com.example.kuitandroidapiexample.model.AnimalType
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
data class ResponseAnimalListDto(
    @SerialName("data")
    val data: List<ResponseAnimalDto>
)

@Serializable
data class ResponseAnimalDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
    @SerialName("state")
    val state: AnimalType,
    @SerialName("breed")
    val breed: String,
    @SerialName("address")
    val address: String
)

@Serializable
data class ResponseAnimalDataDto(
    @SerialName("data")
    val data: ResponseAnimalDto
)
