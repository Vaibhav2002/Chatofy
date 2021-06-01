package com.vaibhav.remote.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String = "Something went wrong. Please try again"
) {
    class Loading<T>() : Resource<T>()
    class Success<T>(data: T? = null, message: String = "") : Resource<T>(data, message)
    class Error<T>(message: String = "Oops something went wrong") : Resource<T>(message = message)
}