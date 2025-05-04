package com.example.kuitandroidapiexample.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.request.RequestRegisterAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalListDto
import com.example.kuitandroidapiexample.data.service.HomeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel : ViewModel() {
    private val homeService: HomeService by lazy { ServicePool.homeService }

    private val _animalListState = mutableStateOf<ResponseAnimalListDto?>(null)
    val animalListState: State<ResponseAnimalListDto?> get() = _animalListState

    fun getTotalAnimalList() {
        homeService.getTotalAnimalList()
            .enqueue(object : Callback<ResponseAnimalListDto> {
                override fun onResponse(
                    call: Call<ResponseAnimalListDto>,
                    response: Response<ResponseAnimalListDto>
                ) {
                    if (response.isSuccessful) {
                        _animalListState.value = response.body()
                    } else {
                        Log.e("getTotalAnimalList", "${response.code()} ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseAnimalListDto>, t: Throwable) {
                    Log.e("getTotalAnimalList", "서버 통신 오류: ${t.message}")
                }
            })
    }

    private val _animalDetailState = mutableStateOf<ResponseAnimalDto?>(null)
    val animalDetailState: State<ResponseAnimalDto?> get() = _animalDetailState

    fun getAnimalDetail(id: Int) {
        homeService.getAnimalDetail(id)
            .enqueue(object : Callback<ResponseAnimalDetailDto> {
                override fun onResponse(
                    call: Call<ResponseAnimalDetailDto>,
                    response: Response<ResponseAnimalDetailDto>
                ) {
                    if (response.isSuccessful) {
                        _animalDetailState.value = response.body()?.data
                    } else {
                        Log.e("getAnimalDetail", "${response.code()} ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseAnimalDetailDto>, t: Throwable) {
                    Log.e("getAnimalDetail", "서버 통신 오류: ${t.message}")
                }
            })
    }

    fun registerAnimal(dto: RequestRegisterAnimalDto) {
        homeService.registerAnimal(dto)
            .enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (!response.isSuccessful) {
                        Log.e("registerAnimal", "${response.code()} ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.e("registerAnimal", "서버 통신 오류: ${t.message}")
                }
            })
    }
    fun deleteAnimal(id: Int){
        homeService.deleteAnimal(id)
            .enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit?>, response: Response<Unit>) {
                    if(!response.isSuccessful){
                        Log.e("deleteAnimal", "${response.code()} ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.e("deleteAnimal", "삭제 실패: ${t.message}")
                }
            })
    }
}