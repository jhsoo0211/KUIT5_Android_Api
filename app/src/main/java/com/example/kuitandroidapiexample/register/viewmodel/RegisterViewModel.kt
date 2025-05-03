package com.example.kuitandroidapiexample.register.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.request.RequestAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalListDto
import com.example.kuitandroidapiexample.data.service.RegisterService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel: ViewModel() {
    private val registerService: RegisterService by lazy { ServicePool.registerService }


    fun postAnAnimal(postData: RequestAnimalDto) {
        registerService.postAnAnimal(postData)
            .enqueue( object: Callback<Unit> {
                override fun onResponse( call: Call<Unit>, response: Response<Unit>
                ) {
                    if (response.isSuccessful) {
                        Log.d("postAnAnimal", "등록 성공 ${response.code()}")
                    } else {
                        Log.e("postAnAnimal", "${response.code()} ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.e("postAnAnimal", "서버 통신 오류: ${t.message}")
                }
            })
    }

}