package com.example.kuitandroidapiexample.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalListDto
import com.example.kuitandroidapiexample.data.service.HomeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel :ViewModel() {
    private val homeService : HomeService by lazy { ServicePool.homeService }

    private val _animalListState = mutableStateOf<ResponseAnimalListDto?>(null)
    val animalListState : State<ResponseAnimalListDto?> get() = _animalListState

    fun getTotalAnimalList(){
        homeService.getTotalAnimalList()
            .enqueue(object:Callback<ResponseAnimalListDto>{
                override fun onResponse(
                    call : Call<ResponseAnimalListDto>,
                    response: Response<ResponseAnimalListDto>
                ){
                    if(response.isSuccessful){
                        _animalListState.value = response.body()
                    }else{
                        Log.e("getTotalAnimalList","${response.code()} $${response.body()}")
                    }
                }
                override fun onFailure(call:Call<ResponseAnimalListDto>, t:Throwable){
                    Log.e("getTotalAnimalList","서버 통신 오류: ${t.message}")
                }
            })
    }

    // 새로운 동물 데이터를 리스트에 추가하는 함수
    fun addSingleAnimalToList(newAnimal: ResponseAnimalDto) {
        val currentList = _animalListState.value?.data?.toMutableList() ?: mutableListOf()
        currentList.add(newAnimal)
        _animalListState.value = _animalListState.value?.copy(data = currentList)
            ?: ResponseAnimalListDto(data = listOf(newAnimal))
    }

}