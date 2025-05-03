package com.example.kuitandroidapiexample.detail.viewmodel

import android.text.BoringLayout
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.request.DeleteDataDto
import com.example.kuitandroidapiexample.data.service.DeleteService
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

class DeleteViewModel : ViewModel(){
    private val deleteService:DeleteService by lazy{ ServicePool.deleteService}

    private val _deleteanimalState = mutableStateOf<DeleteDataDto?>(null)
    val deleteanimalState : State<DeleteDataDto?> get() = _deleteanimalState


    fun deleteAnimal(animalId: Int) {
        deleteService.DeleteAnimal(animalId)
            .enqueue(object : Callback<DeleteDataDto> {
                override fun onResponse(
                    call: Call<DeleteDataDto>,
                    response: Response<DeleteDataDto>
                ) {
                    if(response.isSuccessful){
                        _deleteanimalState.value = response.body()
                    }
                    else{
                        Log.e("deleteAnimal","${response.code()} $${response.body()}")
                    }
                }

                override fun onFailure(call: Call<DeleteDataDto>, t: Throwable) {
                    Log.e("deleteAnimal", "서버 통신 오류: ${t.message}")
                }
            })
    }
}