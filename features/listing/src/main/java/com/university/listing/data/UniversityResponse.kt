package com.university.listing.data

import com.google.gson.annotations.SerializedName

typealias UniversityResponse = List<UniversityData>

data class UniversityData(
    @SerializedName("alpha_two_code")
    val alphaTwoCode: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("domains")
    val domains: List<String>,
    @SerializedName("web_pages")
    val webPages: List<String>,
    @SerializedName("state-province")
    val stateProvince: String?
)