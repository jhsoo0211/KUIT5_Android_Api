package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.response.ResponseSingleAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseSingleAnimalWrapperDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {
    @GET("animals/{id}")
    fun getSingleAnimal(@Path("id")animalId:Int):Call<ResponseSingleAnimalWrapperDto>
}