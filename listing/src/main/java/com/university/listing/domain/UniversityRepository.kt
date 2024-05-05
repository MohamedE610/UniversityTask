package com.university.listing.domain

import com.university.entity.University
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {
    fun getUniversityList(): Flow<List<University>>
}