package com.university.listing.data

import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityRemoteDS {
    @GET("search")
    suspend fun getUniversityList(
        @Query("country") country: String = COUNTRY_QUERY_SEARCH_VALUE
    ): UniversityResponse
}

private const val COUNTRY_QUERY_SEARCH_VALUE = "United Arab Emirates"
