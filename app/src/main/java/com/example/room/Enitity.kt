package com.example.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.Model
import com.example.model.Model2
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "taskk")
@Parcelize
data class Enitity(
    @PrimaryKey(autoGenerate = true)
     var mid: Int? = null,

    @ColumnInfo var results: Model.Result? = null


):Parcelable
