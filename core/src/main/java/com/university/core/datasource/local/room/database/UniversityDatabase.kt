package com.university.core.datasource.local.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.university.core.datasource.local.room.constant.RoomConstants
import com.university.core.datasource.local.room.converters.DBConverters
import com.university.core.datasource.local.room.dao.UniversityDao
import com.university.core.datasource.local.room.entity.UniversityEntity

@Database(
    entities = [UniversityEntity::class],
    version = RoomConstants.DB_VERSION,
    exportSchema = false,
)
@TypeConverters(DBConverters::class)
abstract class UniversityDatabase : RoomDatabase() {

    abstract fun universityDao(): UniversityDao

    companion object {
        @Volatile
        private var INSTANCE: UniversityDatabase? = null

        fun getInstance(context: Context): UniversityDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            UniversityDatabase::class.java,
            RoomConstants.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }
}