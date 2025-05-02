package com.example.kuitandroidapiexample.model

data class AnimalData(
    val id: Int,
    val imageUrl: String,
    val animalName: String,
    val reporterName: String = "조익성",
    val type: AnimalType,
    val address: String
) {
    companion object {

        val animalDataList = mutableListOf(
            AnimalData(
                id = 1,
                imageUrl = "https://cdn.pixabay.com/photo/2018/05/26/18/06/dog-3431913_640.jpg",
                animalName = "점박이",
                type = AnimalType.PROTECT,
                address = "서울특별시 광진구 구의동"
            ),
            AnimalData(
                id = 2,
                imageUrl = "https://cdn.pixabay.com/photo/2018/05/26/18/06/dog-3431913_640.jpg",
                animalName = "점박이",
                type = AnimalType.WITNESS,
                address = "서울특별시 광진구 구의동"
            ),
            AnimalData(
                id = 3,
                imageUrl = "https://cdn.pixabay.com/photo/2018/05/26/18/06/dog-3431913_640.jpg",
                animalName = "점박이",
                type = AnimalType.PROTECT,
                address = "서울특별시 광진구 구의동"
            ),
            AnimalData(
                id = 4,
                imageUrl = "https://cdn.pixabay.com/photo/2018/05/26/18/06/dog-3431913_640.jpg",
                animalName = "점박이",
                type = AnimalType.MISSING,
                address = "서울특별시 광진구 구의동"
            ),
            AnimalData(
                id = 5,
                imageUrl = "https://cdn.pixabay.com/photo/2018/05/26/18/06/dog-3431913_640.jpg",
                animalName = "점박이",
                type = AnimalType.PROTECT,
                address = "서울특별시 광진구 구의동"
            )
        )
    }
}
