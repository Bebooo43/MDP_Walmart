package com.example.walmart.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val password: String
) : Parcelable
