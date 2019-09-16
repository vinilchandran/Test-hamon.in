package com.example.screeningapp.network

import okhttp3.ResponseBody

sealed class RResponse<out T : Any>{
    data class Success<out T : Any>(val data : T) : RResponse<T>()
    data class Error(val error: ResponseBody?)  : RResponse<Nothing>()
}