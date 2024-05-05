package com.university.core.datasource.local.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class DBConverters {
    @TypeConverter
    fun getListOfStringJson(list: List<String>): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun getListOfStringObj(json: String): List<String>? {
        val listType: Type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, listType)
    }
}