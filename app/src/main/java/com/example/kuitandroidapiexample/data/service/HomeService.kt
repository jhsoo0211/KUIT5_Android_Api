package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalListDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalWrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeService {
    @GET("animals")
    fun getTotalAnimalList(): Call<ResponseAnimalListDto>

    @GET("animals/{id}")
    fun getAnimalById(@retrofit2.http.Path("id") id: Int): Call<ResponseAnimalWrapper>

    @POST("animals")
    fun saveAnimal(@Body animal: ResponseAnimalDto): Call<Void>

    @DELETE("animals/{id}")
    fun deleteAnimal(@Path("id") id: Int): Call<Void>
}