package com.example.kuitandroidapiexample.data.dto.request

data class AnimalRequest(
    val data: AnimalData
)

data class AnimalData(
    val id: Int,
    val url: String,
    val name: String,
    val state: String,
    val breed: String,
    val address: String
)

