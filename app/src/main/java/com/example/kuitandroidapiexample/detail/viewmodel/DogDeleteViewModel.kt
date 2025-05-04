package com.example.kuitandroidapiexample.detail.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.request.RequestAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.DeleteErrorResponse
import com.example.kuitandroidapiexample.data.dto.response.DeleteSuccessResponse
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalErrorDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalResultDto
import com.example.kuitandroidapiexample.data.service.HomeService
import com.example.kuitandroidapiexample.navigation.Route
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DogDeleteViewModel : ViewModel() {
    private val deleteDog: HomeService by lazy { ServicePool.homeService }

    fun deleteAnimal(id: Int, onSuccess: () -> Unit) {
        deleteDog.deleteAnimal(id).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val json = response.body()?.string() ?: response.errorBody()?.string()
                json?.let {
                    if (response.isSuccessful) {
                        val success = Json.decodeFromString<DeleteSuccessResponse>(it)
                        Log.d("Delete", "삭제 완료: ${success.data.id}")
                        onSuccess()
                    } else {
                        val error = Json.decodeFromString<DeleteErrorResponse>(it)
                        Log.e("Delete", "삭제 실패: ${error.error}")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Delete", "서버 연결 실패: ${t.message}")
            }
        })
    }
}
