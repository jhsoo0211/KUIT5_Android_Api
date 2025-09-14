package com.example.kuitandroidapiexample.data.dto.response

import com.example.kuitandroidapiexample.model.AnimalType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseAnimalRegisterDto(
    @SerialName("data")
    val data: RequestAnimalRegisterDto
)

@Serializable
data class RequestAnimalRegisterDto(
    @SerialName("id")
    var id: Int,
    @SerialName("breed")
    var breed: String,
    @SerialName("name")
    var name: String,
    @SerialName("url")
    var url: String,
    @SerialName("state")
    var state: AnimalType,
    @SerialName("address")
    var address: String
)