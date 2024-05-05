package com.university.listing.data

import com.university.core.datasource.local.room.dao.UniversityDao
import com.university.core.entity.University
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify


class UniversityRepositoryImplTest {
    @Test
    fun `given getUniversityList() is called, when success, then return the university list`() =
        runTest {
            //arrange
            val data = UniversityData("", "", "", listOf(), listOf(), "")
            val list = listOf(data)
            val expected = listOf(University("", "", "", listOf(), listOf(), ""))
            val remoteDataSource = mock(UniversityRemoteDS::class.java)
            val localDataSource = mock(UniversityDao::class.java)
            val dispatcher = Dispatchers.Unconfined
            val repository = UniversityRepositoryImpl(
                remoteDataSource = remoteDataSource,
                localDateSource = localDataSource,
                dispatcher = dispatcher
            )

            Mockito.`when`(remoteDataSource.getUniversityList())
                .thenReturn(list)

            //act
            val result = repository.getUniversityList().singleOrNull()

            //assert
            Assert.assertEquals(expected, result)
        }

    @Test
    fun `given getUniversityList() is called, when success, then delete all should be called`() =
        runTest {
            //arrange
            val data = UniversityData("", "", "", listOf(), listOf(), "")
            val list = listOf(data)
            val remoteDataSource = mock(UniversityRemoteDS::class.java)
            val localDataSource = mock(UniversityDao::class.java)
            val dispatcher = Dispatchers.Unconfined
            val repository = UniversityRepositoryImpl(
                remoteDataSource = remoteDataSource,
                localDateSource = localDataSource,
                dispatcher = dispatcher
            )

            Mockito.`when`(remoteDataSource.getUniversityList())
                .thenReturn(list)

            //act
            repository.getUniversityList().singleOrNull()

            //assert
            verify(localDataSource, times(1)).deleteAll()
        }

    @Test
    fun `given getUniversityList() is called, when success, then save all should be called`() =
        runTest {
            //arrange
            val data = UniversityData("", "", "", listOf(), listOf(), "")
            val list = listOf(data)
            val remoteDataSource = mock(UniversityRemoteDS::class.java)
            val localDataSource = mock(UniversityDao::class.java)
            val dispatcher = Dispatchers.Unconfined
            val repository = UniversityRepositoryImpl(
                remoteDataSource = remoteDataSource,
                localDateSource = localDataSource,
                dispatcher = dispatcher
            )

            Mockito.`when`(remoteDataSource.getUniversityList())
                .thenReturn(list)

            //act
            repository.getUniversityList().singleOrNull()

            //assert
            verify(localDataSource, times(1)).saveAll(list.map {
                it.toDomain().toUniversityEntity()
            })
        }

    @Test
    fun `given getUniversityList() is called, when fail, then return the cached university list`() =
        runTest {
            //arrange
            val data = UniversityData("", "", "", listOf(), listOf(), "")
            val list = listOf(data.toDomain().toUniversityEntity())
            val expected = listOf(University("", "", "", listOf(), listOf(), ""))
            val remoteDataSource = mock(UniversityRemoteDS::class.java)
            val localDataSource = mock(UniversityDao::class.java)
            val dispatcher = Dispatchers.Unconfined
            val repository = UniversityRepositoryImpl(
                remoteDataSource = remoteDataSource,
                localDateSource = localDataSource,
                dispatcher = dispatcher
            )

            Mockito.`when`(remoteDataSource.getUniversityList())
                .thenAnswer { throw Throwable() }

            Mockito.`when`(localDataSource.getAll())
                .thenReturn(list)

            //act
            val result = repository.getUniversityList().singleOrNull()

            //assert
            Assert.assertEquals(expected, result)
        }

    @Test(expected = Throwable::class)
    fun `given getUniversityList() is called, when failed get remote data and failed to get cached data, then throw error`() =
        runTest {
            //arrange
            val remoteDataSource = mock(UniversityRemoteDS::class.java)
            val localDataSource = mock(UniversityDao::class.java)
            val dispatcher = Dispatchers.Unconfined
            val repository = UniversityRepositoryImpl(
                remoteDataSource = remoteDataSource,
                localDateSource = localDataSource,
                dispatcher = dispatcher
            )

            Mockito.`when`(remoteDataSource.getUniversityList())
                .thenAnswer { throw Throwable() }

            Mockito.`when`(localDataSource.getAll())
                .thenAnswer { throw Throwable() }

            //act
            repository.getUniversityList().singleOrNull()
        }
}