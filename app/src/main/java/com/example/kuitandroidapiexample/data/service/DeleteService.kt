package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDeleteDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path


interface DeleteService{
    @DELETE("animals/{id}")
    fun deleteAnimal(@Path("id") id: Int): Call<ResponseAnimalDeleteDto>
}
