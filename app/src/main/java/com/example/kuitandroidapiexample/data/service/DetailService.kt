package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {
    @GET("animals/{id}")
    fun getAnimalDetail(@Path("id") id: Int) : Call<ResponseAnimalDetailDto>
}