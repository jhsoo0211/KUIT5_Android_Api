package com.example.kuitandroidapiexample.home.viewmodel

import android.R.attr
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.ServicePool.registerService
import com.example.kuitandroidapiexample.data.dto.response.RequestAnimalRegisterDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalRegisterDto

import com.example.kuitandroidapiexample.data.service.RegisterService
import com.example.kuitandroidapiexample.model.AnimalType
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterViewModel: ViewModel() {
    private val RegisterService: RegisterService by lazy { ServicePool.registerService }
    private val _animalState = mutableStateOf<ResponseAnimalRegisterDto?>(null)
    val animalState: State<ResponseAnimalRegisterDto?> get() = _animalState

    private val _registerSuccess = mutableStateOf(false)
    val registerSuccess: State<Boolean> get() = _registerSuccess

    fun registerAnimal(
        name: String,
        url: String,
        state: AnimalType,
        address: String,
        breed: String,
        id: Int

        ) {
        val register = RequestAnimalRegisterDto(
            name = name,
            url = url,
            address = address,
            state = state,
            breed = breed,
            id = id

        )
        viewModelScope.launch{
            runCatching {
                registerService.registerAnimal(register)
            }
                .onSuccess { data ->
                    _animalState.value = data
                }
                .onFailure { error ->
                    Log.e("registerAnimal", error.message ?: "Unknown error")
                }
        }
    }
}