package com.example.kuitandroidapiexample.home.viewmodel


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.ResponseAnimalListDto
import com.example.kuitandroidapiexample.data.dto.request.RequestAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.service.HomeService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    // get 전부
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
                    Log.e("getTotalAnimalList", "서버통신오류: ${t.message}")
                }
            })
    }

    //get 일부
    private val api = ServicePool.homeService

    private val _detail = mutableStateOf<ResponseAnimalDetailDto?>(null)
    val detailState: State<ResponseAnimalDetailDto?> = _detail

    fun getAnimalDetail(id: Int) {
        api.getAnimalDetail(id).enqueue(object : Callback<ResponseAnimalDetailDto> {
            override fun onResponse(
                call: Call<ResponseAnimalDetailDto>,
                response: Response<ResponseAnimalDetailDto>
            ) {
                if (response.isSuccessful) {
                    _detail.value = response.body()
                } else {
                    Log.e("VM", "detail error ${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseAnimalDetailDto>, t: Throwable) {
                Log.e("VM", "detail fail ${t.message}")
            }
        })
    }

    //post
    fun registerAnimal(req: RequestAnimalDto, onResult: (Boolean) -> Unit) {
        ServicePool.homeService.registerAnimal(req)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d("POST", "응답 코드: ${response.code()}")
                    Log.d("POST", "성공 여부: ${response.isSuccessful}")
                    if (!response.isSuccessful) {
                        Log.e("POST", "에러 바디: ${response.errorBody()?.string()}")
                    }
                    onResult(response.isSuccessful)
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("POST", "네트워크 오류: ${t.message}")
                    onResult(false)
                }
            })
    }
    // delete
    fun deleteAnimal(id: Int, onResult: (Boolean) -> Unit) {
        ServicePool.homeService.deleteAnimal(id)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    onResult(response.isSuccessful)
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    onResult(false)
                }
            })
    }

}




