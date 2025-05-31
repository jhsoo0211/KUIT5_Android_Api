package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.response.RequestAnimalRegisterDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDeleteDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalRegisterDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface RegisterService {
    @POST("animals")
    suspend fun registerAnimal(@Body request: RequestAnimalRegisterDto): ResponseAnimalRegisterDto
}
