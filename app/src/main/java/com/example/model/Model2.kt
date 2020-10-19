package com.example.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Model2(
    val seed: String,
    val user:User,
    val version: String
) : Parcelable {

    @Parcelize
    data class User(
        val SSN: String,
        val cell: String,
        val dob: String,
        val email: String,
        val gender: String,
        val location: Location,
        val md5: String,
        val name: Location.Name,
        val password: String,
        val phone: String,
        val picture: String,
        val registered: String,
        val salt: String,
        val sha1: String,
        val sha256: String,
        val username: String
    ) : Parcelable {
        @Parcelize
        data class Location(
            val city: String,
            val state: String,
            val street: String,
            val zip: String
        ) : Parcelable {

            @Parcelize
            data class Name(
                val first: String,
                val last: String,
                val title: String
            ) : Parcelable
        }
    }
}