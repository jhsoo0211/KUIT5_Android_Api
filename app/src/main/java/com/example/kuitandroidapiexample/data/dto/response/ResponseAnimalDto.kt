package com.example.kuitandroidapiexample.data.dto.response

import com.example.kuitandroidapiexample.model.AnimalType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseAnimalListDto(
    @SerialName("data")
    val data: List<ResponseAnimalDto>
)

@Serializable
data class ResponseAnimalDetailDto(
    @SerialName("data") val data: ResponseAnimalDto
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
    val address: String,
)

@Serializable
data class ResponseAnimalResultDto(
    @SerialName("data") val data: ResponseAnimalDto
)

@Serializable
data class ResponseAnimalErrorDto(
    @SerialName("error") val error: String
)

@Serializable
data class DeleteSuccessResponse(
    val message: String,
    val data: DeletedData
)

@Serializable
data class DeletedData(
    val id: Int
)

@Serializable
data class DeleteErrorResponse(
    val error: String
)
