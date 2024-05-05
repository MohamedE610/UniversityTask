package com.university.entity

data class University(
    val alphaTwoCode: String,
    val name: String,
    val country: String,
    val domains: List<String>,
    val webPages: List<String>,
    val stateProvince: String
)