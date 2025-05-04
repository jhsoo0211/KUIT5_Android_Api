package com.example.kuitandroidapiexample.model

import android.R.attr.name
import android.R.attr.type

//"id": 1,
//"name": "박지원",
//"url": "https://example.com/item1",
//"state": "PROTECT",
//"breed": "말티즈",
//"address": "건국대학교 공학관"

data class AnimalData(
//    val id: Int,
//    val imageUrl: String,
//    val animalName: String,
//    val reporterName: String = "조익성",
//    val type: AnimalType,
//    val address: String
    val id: Int,
    val url: String,
    val animalName: String = "점박이",
    val name: String,
    val reporterName: String = "조익성",
    val breed: String = "말티즈",
    val state: AnimalType,
    val address: String
) {
    companion object {

        val animalDataList = listOf(
            AnimalData(
                id = 1,
                url = "https://cdn.pixabay.com/photo/2018/05/26/18/06/dog-3431913_640.jpg",
                animalName = "점박이",
                name = "조익성",
                state = AnimalType.PROTECT,
                address = "서울특별시 광진구 구의동"
            ),
//            AnimalData(
//                id = 2,
//                imageUrl = "https://cdn.pixabay.com/photo/2018/05/26/18/06/dog-3431913_640.jpg",
//                animalName = "점박이",
//                type = AnimalType.WITNESS,
//                address = "서울특별시 광진구 구의동"
//            ),
//            AnimalData(
//                id = 3,
//                imageUrl = "https://cdn.pixabay.com/photo/2018/05/26/18/06/dog-3431913_640.jpg",
//                animalName = "점박이",
//                type = AnimalType.PROTECT,
//                address = "서울특별시 광진구 구의동"
//            ),
//            AnimalData(
//                id = 4,
//                imageUrl = "https://cdn.pixabay.com/photo/2018/05/26/18/06/dog-3431913_640.jpg",
//                animalName = "점박이",
//                type = AnimalType.MISSING,
//                address = "서울특별시 광진구 구의동"
//            ),
//            AnimalData(
//                id = 5,
//                imageUrl = "https://cdn.pixabay.com/photo/2018/05/26/18/06/dog-3431913_640.jpg",
//                animalName = "점박이",
//                type = AnimalType.PROTECT,
//                address = "서울특별시 광진구 구의동"
//            )
        )
    }
}
