package com.university.core.datasource.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.university.core.datasource.local.room.constant.RoomConstants
import com.university.core.datasource.local.room.converters.DBConverters

@Entity(tableName = RoomConstants.TABLE_NAME)
data class UniversityEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "alphaTwoCode")
    val alphaTwoCode: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "country")
    val country: String,
    @TypeConverters(DBConverters::class)
    @ColumnInfo(name = "domains")
    val domains: List<String>,
    @TypeConverters(DBConverters::class)
    @ColumnInfo(name = "webPages")
    val webPages: List<String>,
    @ColumnInfo(name = "stateProvince")
    val stateProvince: String
)