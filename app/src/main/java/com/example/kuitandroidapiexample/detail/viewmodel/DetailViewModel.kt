package com.example.kuitandroidapiexample.detail.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.service.DetailService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val detailService: DetailService by lazy { ServicePool.detailService }

    private val _animalDetailState = mutableStateOf<ResponseAnimalDetailDto?>(null)
    val animalDetailState: State<ResponseAnimalDetailDto?> get() = _animalDetailState

    fun getAnimalDetail(id: Int) {
        detailService.getAnimalDetail(id)
            .enqueue(object : Callback<ResponseAnimalDetailDto> {
                override fun onResponse(
                    call: Call<ResponseAnimalDetailDto>,
                    response: Response<ResponseAnimalDetailDto>
                ) {
                    if (response.isSuccessful) {
                        _animalDetailState.value = response.body()
                    } else {
                        Log.e("getAnimalDetail", "${response.code()} ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseAnimalDetailDto>, t: Throwable) {
                    Log.e("getAnimalDetail", "서버 통신 오류: ${t.message}")
                }
            })
    }

    fun deleteAnimal(id: Int) {
        detailService.deleteAnimal(id)
            .enqueue(object : Callback<ResponseAnimalDetailDto> {
                override fun onResponse(
                    call: Call<ResponseAnimalDetailDto>,
                    response: Response<ResponseAnimalDetailDto>
                ) {
                    if (response.isSuccessful) {
                        _animalDetailState.value = response.body()
                    } else {
                        Log.e("getAnimalDetail", "${response.code()} ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseAnimalDetailDto>, t: Throwable) {
                    Log.e("getAnimalDetail", "서버 통신 오류: ${t.message}")
                }
            })
    }
}