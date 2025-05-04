package com.example.kuitandroidapiexample.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.example.kuitandroidapiexample.model.AnimalType

@Serializable
data class RequestAnimalDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("url")
    val url: String,
    @SerialName("name")
    val name: String,
    @SerialName("state")
    val state: AnimalType,
    @SerialName("breed")
    val breed: String,
    @SerialName("address")
    val address: String
)
