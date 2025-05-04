package com.example.kuitandroidapiexample.detail.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDetailDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalListDto
import com.example.kuitandroidapiexample.data.service.HomeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DogDetailViewModel : ViewModel() {
    private val dogDetail: HomeService by lazy { ServicePool.homeService }

    private val _dogDetailState = mutableStateOf<ResponseAnimalDetailDto?>(null)
    val dogDetailState: State<ResponseAnimalDetailDto?> get() = _dogDetailState

    fun getDogDetail(id: Int) {
        dogDetail.getAnimalDetail(id)
            .enqueue(object : Callback<ResponseAnimalDetailDto> {
                override fun onResponse(
                    call: Call<ResponseAnimalDetailDto>,
                    response: Response<ResponseAnimalDetailDto>
                ){
                    if(response.isSuccessful){
                        _dogDetailState.value = response.body();
                    }else{
                        Log.e("getDogDetail", "${response.code()} ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseAnimalDetailDto>, t: Throwable){
                    Log.e("getDogDetail", "서버 통신 오류: ${t.message}")
                }
            })
    }
}
