package com.example.kuitandroidapiexample.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.ServicePool.deleteService
import com.example.kuitandroidapiexample.data.ServicePool.detailService
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDeleteDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.service.DeleteService
import com.example.kuitandroidapiexample.data.service.DetailService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteViewModel: ViewModel() {
    private val DeleteService: DeleteService by lazy { ServicePool.deleteService }
    private val _animalState = mutableStateOf<ResponseAnimalDeleteDto?>(null)
    val animalState: State<ResponseAnimalDeleteDto?> get() = _animalState

    fun deleteAnimal(id: Int) {
        viewModelScope.launch {
            runCatching {
                deleteService.deleteAnimal(id)
            }
                .onSuccess { data ->
                _animalState.value = data
            }
                .onFailure { error ->
                    Log.e("deleteAnimal", error.message ?: "Unknown error")
                }
        }
    }
}