package com.example.kuitandroidapiexample.detail.viewmodel

import android.util.Log
import android.util.Log.i
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.ServicePool.homeService
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalListDto
import com.example.kuitandroidapiexample.data.service.DetailService
import com.example.kuitandroidapiexample.data.service.HomeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val detailService: DetailService by lazy { ServicePool.detailService }

    private val _animalState = mutableStateOf<ResponseAnimalDto?>(null)
    val animalState: State<ResponseAnimalDto?> get() = _animalState

    fun getAnAnimal(id: Int) {
        detailService.getAnAnimal(id)
            .enqueue(object : Callback<ResponseAnimalDto> {
                override fun onResponse(
                    call: Call<ResponseAnimalDto>,
                    response: Response<ResponseAnimalDto>
                ) {
                    if (response.isSuccessful) {
                        _animalState.value = response.body()
                    } else {
                        Log.e("getAnAnimal", "${response.code()} ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseAnimalDto>, t: Throwable) {
                    Log.e("getAnAnimal", "서버 통신 오류: ${t.message}")
                }
            })
    }
}