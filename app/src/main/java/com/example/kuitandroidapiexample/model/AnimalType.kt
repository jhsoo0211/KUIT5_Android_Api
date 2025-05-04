package com.example.kuitandroidapiexample.model

import kotlinx.serialization.SerialName

enum class AnimalType(
    val type: String
) {
    @SerialName("PROTECT")
    PROTECT("보호중"),
    @SerialName("MISSING")
    MISSING("실종신고"),
    @SerialName("WITNESS")
    WITNESS("목격신고")
    //PROTECT("보호중"),
    //MISSING("실종신고"),
    //WITNESS("목격신고")
}