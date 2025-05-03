package com.example.kuitandroidapiexample.detail.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.response.ResponseSingleAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseSingleAnimalWrapperDto
import com.example.kuitandroidapiexample.data.service.DetailService
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

class DetailViewModel : ViewModel(){
    private val detailService:DetailService by lazy{ServicePool.detailService}

    private val _animalSingleState = mutableStateOf<ResponseSingleAnimalWrapperDto?>(null)
    val animalsingleState:State<ResponseSingleAnimalWrapperDto?>get() = _animalSingleState

    fun getSingleAnimalDetail(animalId:Int){
        detailService.getSingleAnimal(animalId)
            .enqueue(object : Callback<ResponseSingleAnimalWrapperDto>{
                override  fun onResponse(
                    call : Call<ResponseSingleAnimalWrapperDto>,
                    response:Response<ResponseSingleAnimalWrapperDto>
                ){
                    if(response.isSuccessful){
                        _animalSingleState.value = response.body()
                    }else{
                        Log.e("getSingleAnimalDetail","${response.code()} ${response.message()}")
                    }
                }
                override fun onFailure(call:Call<ResponseSingleAnimalWrapperDto>, t:Throwable){
                   Log.e("getSingleAnimalDetail","서버 통신 오류: ${t.message}")
                }
            })
    }
}