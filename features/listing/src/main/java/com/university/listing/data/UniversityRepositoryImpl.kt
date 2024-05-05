package com.university.listing.data

import com.university.core.datasource.local.room.dao.UniversityDao
import com.university.core.di.qualifier.Dispatcher
import com.university.core.di.qualifier.DispatcherKey
import com.university.core.di.scope.FragmentScope
import com.university.core.extension.emitFlow
import com.university.core.entity.University
import com.university.listing.domain.UniversityRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@FragmentScope
class UniversityRepositoryImpl @Inject constructor(
    private val remoteDataSource: UniversityRemoteDS,
    private val localDateSource: UniversityDao,
    @Dispatcher(DispatcherKey.IO) private val dispatcher: CoroutineDispatcher
) : UniversityRepository {
    override fun getUniversityList(): Flow<List<University>> {
        return emitFlow { remoteDataSource.getUniversityList().map { it.toDomain() } }
            .catch { emit(localDateSource.getAll().map { it.toDomain() }) }
            .onEach { list ->
                localDateSource.deleteAll()
                localDateSource.saveAll(list.map { university -> university.toUniversityEntity() })
            }.flowOn(dispatcher)
    }
}