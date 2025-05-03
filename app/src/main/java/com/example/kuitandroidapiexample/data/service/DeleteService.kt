package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.request.DeleteDataDto
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Path

interface DeleteService {
    @DELETE("animals/{id}")
    fun DeleteAnimal(@Path("id")animalId:Int): Call<DeleteDataDto>
}