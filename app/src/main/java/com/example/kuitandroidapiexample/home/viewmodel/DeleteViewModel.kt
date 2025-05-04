package com.example.kuitandroidapiexample.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.ServicePool.deleteService
import com.example.kuitandroidapiexample.data.ServicePool.detailService
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDeleteDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.service.DeleteService
import com.example.kuitandroidapiexample.data.service.DetailService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteViewModel: ViewModel() {
    private val DeleteService: DeleteService by lazy { ServicePool.deleteService }
    private val _animalState = mutableStateOf<ResponseAnimalDeleteDto?>(null)
    val animalState: State<ResponseAnimalDeleteDto?> get() = _animalState

    fun deleteAnimal(id: Int) {
        deleteService.deleteAnimal(id)
            .enqueue(object : Callback<ResponseAnimalDeleteDto> {
                override fun onResponse(
                    call: Call<ResponseAnimalDeleteDto>,
                    response: Response<ResponseAnimalDeleteDto>
                ){
                    if(response.isSuccessful){
                        _animalState.value = response.body()
                    } else{
                        Log.e("deleteAnimal", "${response.code()} ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<ResponseAnimalDeleteDto>, t: Throwable) {
                    Log.e("deleteAnimal", "서버 통신 오류: ${t.message}")
                }
            })
    }
}