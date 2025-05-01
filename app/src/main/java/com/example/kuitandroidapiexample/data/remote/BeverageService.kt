package com.example.kuitandroidapiexample.data.remote

import retrofit2.http.GET

interface BeverageService {
    @GET("beverage/detail") // ← 실제 API 경로로 수정해줘!
    suspend fun getBeverageDetail(): BeverageDetailResponse
}