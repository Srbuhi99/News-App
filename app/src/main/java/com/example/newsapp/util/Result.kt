package com.example.newsapp.util


sealed class Result<out T: Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Fail(val message: String, val exception: Exception) : Result<Nothing>()
}
