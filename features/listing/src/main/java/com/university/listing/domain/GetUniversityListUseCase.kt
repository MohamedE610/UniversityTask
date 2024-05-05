package com.university.listing.domain

import com.university.entity.University
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUniversityListUseCase @Inject constructor(
    private val repository: UniversityRepository
) {
    operator fun invoke(): Flow<List<University>> {
        return repository.getUniversityList()
    }
}