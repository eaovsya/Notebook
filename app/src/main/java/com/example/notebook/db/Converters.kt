package com.example.notebook.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromArray(strings: List<String?>?): String? {
        return Gson().toJson(strings)
    }

    @TypeConverter
    fun toArray(jsonString: String?): List<String?>? {
        val listType = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson<List<String>>(jsonString, listType)
    }
}