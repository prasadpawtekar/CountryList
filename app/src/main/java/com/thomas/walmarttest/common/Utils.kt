package com.thomas.walmarttest.common

import android.content.Context
import android.net.ConnectivityManager
import java.net.ConnectException
import java.net.SocketTimeoutException

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val activeNetworkInfo = connectivityManager?.activeNetwork


    return activeNetworkInfo != null
}

fun Exception.getInfo(): String {
    return when(this) {
        is ConnectException -> "Failed to connect to server."
        is SocketTimeoutException -> "Server is taking longer than usual to process request. Please retry."
        else -> this.toString()
    }
}