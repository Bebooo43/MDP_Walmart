package com.example.walmart.utils

import android.content.Context
import android.util.Patterns


fun String.isValidEmail(): Boolean =
    this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun Context.getDrawableIdByName(drawableName: String) =
    resources.getIdentifier(drawableName , "drawable", packageName)