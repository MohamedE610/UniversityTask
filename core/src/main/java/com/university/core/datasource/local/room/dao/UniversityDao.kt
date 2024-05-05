package com.university.core.datasource.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.university.core.datasource.local.room.constant.RoomConstants
import com.university.core.datasource.local.room.entity.UniversityEntity

@Dao
interface UniversityDao {
    @Query("SELECT * FROM ${RoomConstants.TABLE_NAME}")
    suspend fun getAll(): List<UniversityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(list: List<UniversityEntity>)

    @Query("DELETE FROM ${RoomConstants.TABLE_NAME}")
    suspend fun deleteAll(): Int
}