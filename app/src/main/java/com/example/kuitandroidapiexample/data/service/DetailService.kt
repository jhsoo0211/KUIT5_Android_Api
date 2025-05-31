package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.response.BaseResponse
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {
    @GET("animals/{id}")
    suspend fun getAnimal(@Path("id") id: Int): BaseResponse<ResponseAnimalDetailDto>
}