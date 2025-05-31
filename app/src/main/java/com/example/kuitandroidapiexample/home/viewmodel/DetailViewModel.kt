package com.example.kuitandroidapiexample.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.ServicePool.detailService
import com.example.kuitandroidapiexample.data.ServicePool.homeService
import com.example.kuitandroidapiexample.data.dto.response.BaseResponse
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.service.DetailService
import com.example.kuitandroidapiexample.data.service.HomeService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    private val DetailService: DetailService by lazy { ServicePool.detailService }
    private val _animalState = mutableStateOf<BaseResponse<ResponseAnimalDetailDto>?>(null)
    val animalState: State<BaseResponse<ResponseAnimalDetailDto>?> get() = _animalState

    fun getAnimal(id: Int) {
        viewModelScope.launch {
            runCatching {
                detailService.getAnimal(id)
            }
                .onSuccess { data ->
                    _animalState.value = data
                }
                .onFailure { error ->
                    Log.e("getAnimal", error.message ?: "Unknown error")
                }
        }
    }
}