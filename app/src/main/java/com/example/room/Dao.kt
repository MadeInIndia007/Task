package com.example.moviesdemo.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room.Enitity

@Dao
public interface Dao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(item: Enitity)

    @Query("SELECT * FROM taskk")
    fun getAll(): LiveData<List<Enitity>>


}
