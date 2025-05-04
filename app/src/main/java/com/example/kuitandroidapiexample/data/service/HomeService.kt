package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.RequestAnimalDto
import com.example.kuitandroidapiexample.data.dto.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.ResponseAnimalListDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface HomeService {
    @GET("animals") // 전체동물조회 //반드시 endpoint 추가해야!(정확한 경로명시)

    fun getTotalAnimalList(): Call<ResponseAnimalListDto>

    @GET("animals/{id}") // 단일동물조회
    fun getAnimalById(@Path("id") id: Int): Call<ResponseAnimalDetailDto>

    @POST("animals") // 동물등록
    fun registerAnimal(@Body request: RequestAnimalDto): Call<Unit>


    @DELETE("animals/{id}") // 동물삭제
    fun deleteAnimal(@Path("id") id: Int): Call<Unit>


}