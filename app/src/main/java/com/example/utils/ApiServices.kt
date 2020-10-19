package com.example.moviesdemo.util


import android.graphics.ColorSpace
import com.example.model.Model
import com.example.room.Enitity
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiServices {


    @GET("api/0.4/?randomapi")
    fun getData(
    ): Observable<Model>


}