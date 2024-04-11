package com.maxidev.newsyork.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun dateTimeUtils(dateTime: String): String {
    val dt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm")

    val dtFormat = dt.parse(dateTime)

    return format.format(dtFormat ?: "")
}