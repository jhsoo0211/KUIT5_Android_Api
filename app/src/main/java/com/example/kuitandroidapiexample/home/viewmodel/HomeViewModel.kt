package com.example.kuitandroidapiexample.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.RequestAnimalDto
import com.example.kuitandroidapiexample.data.dto.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.dto.ResponseAnimalListDto
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
            .enqueue(object : Callback<ResponseAnimalListDto>{
                override fun onResponse(
                    call : Call<ResponseAnimalListDto>,
                    response: Response<ResponseAnimalListDto>

                ) {
                    if (response.isSuccessful) {
                        val list = response.body()?.data.orEmpty()
                        Log.d("HomeVM", "getTotalAnimalList 성공! items=${list.size}")
                        _animalListState.value = response.body()
                    } else {
                        Log.e("HomeVM", "getTotalAnimalList 실패 코드=${response.code()}")
                    }
                }
                override fun onFailure(call: Call<ResponseAnimalListDto>, t: Throwable){
                    Log.e("getTotalAnimalList", "서버 통신 오류: ${t.message}")

                }

            })
    }

    private val _animalDetailState = mutableStateOf<ResponseAnimalDto?>(null)
    val animalDetailState: State<ResponseAnimalDto?> get() = _animalDetailState

    fun getAnimalById(id: Int) {
        homeService.getAnimalById(id)
            .enqueue(object : Callback<ResponseAnimalDetailDto> {
                override fun onResponse(
                    call: Call<ResponseAnimalDetailDto>,
                    response: Response<ResponseAnimalDetailDto>
                ) {
                    if (response.isSuccessful) {
                        _animalDetailState.value = response.body()?.data
                    } else {
                        Log.e("getAnimalById", "조회 실패: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<ResponseAnimalDetailDto>, t: Throwable) {
                    Log.e("getAnimalById", "통신 실패: ${t.message}")
                }
            })
    }


    fun registerAnimal(animal: RequestAnimalDto) {
        homeService.registerAnimal(animal)
            .enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        Log.d("registerAnimal", "등록 성공!")
                        getTotalAnimalList()
                    } else {
                        Log.e("registerAnimal", "등록 실패: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.e("registerAnimal", "통신 실패: ${t.message}")
                }
            })
    }

    fun deleteAnimal(id: Int) {
        homeService.deleteAnimal(id)
            .enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if (response.isSuccessful) {
                        Log.d("deleteAnimal", "삭제 성공!")
                        getTotalAnimalList() // 삭제 후 목록 갱신
                    } else {
                        Log.e("deleteAnimal", "삭제 실패: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.e("deleteAnimal", "통신 실패: ${t.message}")
                }
            })
    }




}