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
data class ResponseAnimalDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String, //개이름
    @SerialName("url")
    val url: String,
    @SerialName("state")
    val state: AnimalType,
    @SerialName("breed")
    val breed: String, //종류
    @SerialName("address")
    val address: String
)
