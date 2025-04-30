package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDataDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalListDto
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {
    @GET("animals/{id}")
    fun getAnAnimal(@Path("id") id: Int): Call<ResponseAnimalDataDto>

    @DELETE("animals/{id}")
    fun deleteAnimal(@Path("id") id: Int): Call<Unit>
}