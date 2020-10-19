package com.example.room

import androidx.room.TypeConverter
import com.example.model.Model
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyTypeConverters {
    @TypeConverter
    fun toObject(value: String): Model.Result {
        return Gson().fromJson(value, Model.Result::class.java)
    }

    @TypeConverter
    fun toJson(model: Model.Result): String {
        return Gson().toJson(model).toString()
    }
}