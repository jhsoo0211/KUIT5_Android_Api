package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.request.RequestAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalListDto
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeService {
    @GET("animals")
    fun getTotalAnimalList() : Call<ResponseAnimalListDto>

    @GET("animals/{id}")
    fun getAnimalDetail(
        @Path("id") id: Int
    ): Call<ResponseAnimalDetailDto>

    @POST("animals")
    fun postAnimal(
        @Body animal: RequestAnimalDto
    ): Call<ResponseBody>

    @DELETE("animals/{id}")
    fun deleteAnimal(
        @Path("id") id: Int
    ): Call<ResponseBody>
}