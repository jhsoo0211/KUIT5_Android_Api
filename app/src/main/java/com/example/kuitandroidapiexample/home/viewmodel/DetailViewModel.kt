package com.example.kuitandroidapiexample.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.ServicePool.detailService
import com.example.kuitandroidapiexample.data.ServicePool.homeService
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.service.DetailService
import com.example.kuitandroidapiexample.data.service.HomeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    private val DetailService: DetailService by lazy { ServicePool.detailService }
    private val _animalState = mutableStateOf<ResponseAnimalDetailDto?>(null)
    val animalState: State<ResponseAnimalDetailDto?> get() = _animalState

    fun getAnimal(id: Int) {
        detailService.getAnimal(id)
            .enqueue(object : Callback<ResponseAnimalDetailDto> {
                override fun onResponse(
                    call: Call<ResponseAnimalDetailDto>,
                    response: Response<ResponseAnimalDetailDto>
                ){
                    if(response.isSuccessful){
                        _animalState.value = response.body()
                    } else{
                        Log.e("getAnimal", "${response.code()} ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<ResponseAnimalDetailDto>, t: Throwable) {
                    Log.e("getAnimal", "서버 통신 오류: ${t.message}")
                }
            })
    }
}