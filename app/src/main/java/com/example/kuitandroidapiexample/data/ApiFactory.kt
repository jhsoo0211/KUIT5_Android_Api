package com.example.kuitandroidapiexample.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.example.kuitandroidapiexample.BuildConfig
import com.example.kuitandroidapiexample.data.service.HomeService


object ApiFactory {



    private val BASE_URL: String = BuildConfig.BASE_URL

    private val client = OkHttpClient.Builder()
        .build()
    private val json = Json {
        ignoreUnknownKeys = true
    }
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)

}

object ServicePool {
    val homeService = ApiFactory.create<HomeService>()
}