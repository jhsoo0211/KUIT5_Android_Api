package com.example.kuitandroidapiexample.data.service

import com.example.kuitandroidapiexample.data.dto.ResponseAnimalListDto
import retrofit2.Call
import retrofit2.http.GET

interface HomeService {
    @GET("animals") //GET DELET 도 가능
    fun getTotalAnimalList(): Call<ResponseAnimalListDto>  //매개변수 필요 시 괄호 안에 넣기
}