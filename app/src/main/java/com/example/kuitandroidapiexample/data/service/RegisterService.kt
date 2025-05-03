package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.request.RequestAnimalDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("animals")
    fun registerAnimal(@Body request: RequestAnimalDto): Call<Unit>
}