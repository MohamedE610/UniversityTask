package com.university.listing.data

import com.university.core.datasource.local.room.entity.UniversityEntity
import com.university.entity.University


fun UniversityData.toDomain(): University {
    return University(
        alphaTwoCode = alphaTwoCode,
        name = name,
        country = country,
        domains = domains,
        webPages = webPages,
        stateProvince = stateProvince
    )
}

fun UniversityEntity.toDomain(): University {
    return University(
        alphaTwoCode = alphaTwoCode,
        name = name,
        country = country,
        domains = domains,
        webPages = webPages,
        stateProvince = stateProvince
    )
}

fun University.toUniversityEntity() = UniversityEntity(
    alphaTwoCode = alphaTwoCode,
    name = name,
    country = country,
    domains = domains,
    webPages = webPages,
    stateProvince = stateProvince
)
