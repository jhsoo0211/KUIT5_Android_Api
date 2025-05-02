package com.example.kuitandroidapiexample.home.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kuitandroidapiexample.data.ServicePool
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalListDto
import com.example.kuitandroidapiexample.data.dto.response.ResponseAnimalWrapper
import com.example.kuitandroidapiexample.data.service.HomeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val homeService: HomeService by lazy { ServicePool.homeService }

    private val _animalListState = mutableStateOf<ResponseAnimalListDto?>(null)
    val animalListState: State<ResponseAnimalListDto?> get() = _animalListState

    private val _singleAnimalState = mutableStateOf<ResponseAnimalDto?>(null)
    val singleAnimalState: State<ResponseAnimalDto?> get() = _singleAnimalState

    private val _isAnimalSaved = mutableStateOf(false)
    val isAnimalSaved: State<Boolean> get() = _isAnimalSaved

    private val _isAnimalDeleted = mutableStateOf(false)
    val isAnimalDeleted: State<Boolean> get() = _isAnimalDeleted

    fun getTotalAnimalList() {
        homeService.getTotalAnimalList()
            .enqueue(object : Callback<ResponseAnimalListDto> {
                override fun onResponse(
                    call: Call<ResponseAnimalListDto>,
                    response: Response<ResponseAnimalListDto>
                ) {
                    if (response.isSuccessful) {
                        _animalListState.value = response.body()
                        Log.d("getTotalAnimalList", "성공: ${response.body()}")
                    } else {
                        Log.e("getTotalAnimalList", "실패: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseAnimalListDto>, t: Throwable) {
                    Log.e("getTotalAnimalList", "서버 통신 오류: ${t.message}")
                }
            })
    }

    fun getAnimalById(id: Int) {
        homeService.getAnimalById(id)
            .enqueue(object : Callback<ResponseAnimalWrapper> {
                override fun onResponse(
                    call: Call<ResponseAnimalWrapper>,
                    response: Response<ResponseAnimalWrapper>
                ) {
                    if (response.isSuccessful) {
                        Log.d("getAnimalById", "단건 조회 성공: ${response.body()}")
                        _singleAnimalState.value = response.body()?.data
                    } else {
                        Log.e("getAnimalById", "단건 조회 실패: ${response.code()} - ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseAnimalWrapper>, t: Throwable) {
                    Log.e("getAnimalById", "서버 통신 오류: ${t.message}")
                }
            })
    }

    fun saveAnimal(animal: ResponseAnimalDto) {
        homeService.saveAnimal(animal)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        _isAnimalSaved.value = true
                        Log.d("saveAnimal", "저장 성공")
                        getTotalAnimalList()
                    } else {
                        Log.e("saveAnimal", "저장 실패: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("saveAnimal", "서버 통신 오류: ${t.message}")
                }
            })
    }

    fun deleteAnimal(id: Int) {
        homeService.deleteAnimal(id)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Log.d("deleteAnimal", "삭제 성공: $id")
                        _isAnimalDeleted.value = true
                        getTotalAnimalList()
                    } else {
                        Log.e("deleteAnimal", "삭제 실패: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.e("deleteAnimal", "서버 통신 오류: ${t.message}")
                }
            })
    }
}
