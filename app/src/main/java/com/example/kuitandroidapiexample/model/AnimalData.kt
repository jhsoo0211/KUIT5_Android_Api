package com.example.kuitandroidapiexample.model

import com.example.kuitandroidapiexample.data.dto.ResponseAnimalDto

data class AnimalData(
    val imageUrl: String,
    val animalName: String,
    val reporterName: String = "조익성",
    val type: AnimalType,
    val address: String
) {
    companion object {

        val animalDataList = listOf(
            AnimalData(
                imageUrl = "https://cdn.pixabay.com/photo/2018/05/26/18/06/dog-3431913_640.jpg",
                animalName = "점박이",
                type = AnimalType.PROTECT,
                address = "서울특별시 광진구 구의동"
            ),
            AnimalData(
                imageUrl = "https://cdn.pixabay.com/photo/2018/05/26/18/06/dog-3431913_640.jpg",
                animalName = "점박이",
                type = AnimalType.WITNESS,
                address = "서울특별시 광진구 구의동"
            ),
            AnimalData(
                imageUrl = "https://cdn.pixabay.com/photo/2018/05/26/18/06/dog-3431913_640.jpg",
                animalName = "점박이",
                type = AnimalType.PROTECT,
                address = "서울특별시 광진구 구의동"
            ),
            AnimalData(
                imageUrl = "https://cdn.pixabay.com/photo/2018/05/26/18/06/dog-3431913_640.jpg",
                animalName = "점박이",
                type = AnimalType.MISSING,
                address = "서울특별시 광진구 구의동"
            ),
            AnimalData(
                imageUrl = "https://cdn.pixabay.com/photo/2018/05/26/18/06/dog-3431913_640.jpg",
                animalName = "점박이",
                type = AnimalType.PROTECT,
                address = "서울특별시 광진구 구의동"
            )
        )
    }
}


fun AnimalData.toResponseDto(): ResponseAnimalDto {
    return ResponseAnimalDto(
        id = 0, // 샘플용 더미라서 0 넣는 거고 실제론 필요시 id 필드 추가
        url = this.imageUrl,
        name = this.animalName,
        state = this.type,
        breed = "", // 필요에 따라 추가
        address = this.address
    )
}
