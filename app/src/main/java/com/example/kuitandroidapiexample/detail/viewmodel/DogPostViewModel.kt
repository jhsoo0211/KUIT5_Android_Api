package com.example.kuitandroidapiexample.detail.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.request.RequestAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalErrorDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalResultDto
import com.example.kuitandroidapiexample.data.service.HomeService
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DogPostViewModel: ViewModel() {
    private val postDog: HomeService by lazy { ServicePool.homeService }

    private val _dogDetailState = mutableStateOf<ResponseAnimalDetailDto?>(null)
    val dogDetailState: State<ResponseAnimalDetailDto?> get() = _dogDetailState

    fun postAnimal(animal: RequestAnimalDto) {
        postDog.postAnimal(animal).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        val json = body.string()
                        val parsed = Json.decodeFromString<ResponseAnimalResultDto>(json)
                        Log.d("Success", "등록 성공: ${parsed.data.name}")
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val parsedError = errorBody?.let {
                        Json.decodeFromString<ResponseAnimalErrorDto>(it)
                    }
                    Log.e("Error", "등록 실패: ${parsedError?.error}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("postAnimal", "네트워크 오류: ${t.message}")
            }
        })
    }
}