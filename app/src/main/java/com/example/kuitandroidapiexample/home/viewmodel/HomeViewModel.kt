package com.example.kuitandroidapiexample.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.ServicePool.homeService
import com.example.kuitandroidapiexample.data.dto.response.BaseResponse
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.service.HomeService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val HomeService: HomeService by lazy { ServicePool.homeService }

    private val _animalListState = mutableStateOf<BaseResponse<List<ResponseAnimalDto>>?>(null)
    val animalListState: State<BaseResponse<List<ResponseAnimalDto>>?> get() = _animalListState

    fun getTotalAnimalList() {
        viewModelScope.launch {
            runCatching {
                homeService.getTotalAnimalList()
            }
                .onSuccess { data ->
                    _animalListState.value = data
                }
                .onFailure { error ->
                    //Log.e("getTotalAnimalList", error.message ?: "Unknown error")
                    Log.e("getTotalAnimalList", "API call failed", error)
                }
        }

    }


}

