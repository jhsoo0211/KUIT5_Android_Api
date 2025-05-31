package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.response.BaseResponse
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto

import retrofit2.Call
import retrofit2.http.GET

interface HomeService {
    @GET("animals")
    suspend fun getTotalAnimalList(): BaseResponse<List<ResponseAnimalDto>>
}