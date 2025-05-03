package com.example.kuitandroidapiexample.register.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalListDto
import com.example.kuitandroidapiexample.data.service.RegisterService
import com.example.kuitandroidapiexample.model.AnimalType
import kotlinx.serialization.SerialName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel(){
    private val registerService : RegisterService by lazy{ ServicePool.registerService}

    private val _animalRegistState = mutableStateOf<ResponseAnimalDto?>(null)
    val animalRegistState : State<ResponseAnimalDto?> get() = _animalRegistState

    //등록하기 버튼의 onclick에 쓸 함수
    fun registerNewAnimal(
        url:String,
        animalName:String,
        address:String,
        reporterName:String,
        state: AnimalType,
    ) {
        val requestDto = ResponseAnimalDto(
            id=0,
            url=url,
            name=animalName,
            state=state,
            breed = reporterName,
            address = address
        )
        registerService.RegisterAnimal(requestDto)
            .enqueue(object: Callback<ResponseAnimalDto>{
                override fun onResponse(
                    call : Call<ResponseAnimalDto>,
                    response:Response<ResponseAnimalDto>
                ){
                    if(response.isSuccessful){
                        _animalRegistState.value = response.body()
                    }else{
                        Log.e("animalRegistState","${response.code()} $${response.body()}")
                    }
                }
                override fun onFailure(call:Call<ResponseAnimalDto>, t:Throwable){
                    Log.e("animalRegistState","서버 통신 오류: ${t.message}")
                }
            })
    }
}