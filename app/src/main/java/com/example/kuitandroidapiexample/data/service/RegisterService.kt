package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("animals")
    fun RegisterAnimal(@Body request : ResponseAnimalDto ): Call<ResponseAnimalDto>
}