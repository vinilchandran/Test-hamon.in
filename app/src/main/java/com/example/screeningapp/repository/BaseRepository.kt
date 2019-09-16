package com.example.screeningapp.repository

import android.util.Log
import com.example.screeningapp.network.Output
import com.example.screeningapp.network.RResponse
import retrofit2.Response
import java.io.IOException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody


open class BaseRepository {
    suspend fun <T : Any> safeApiCall(call : suspend()-> Response<T>, error : String) :  T?{
        val result = apiOutput(call, error)
        var output : T? = null
        when(result){
            is Output.Success ->
                output = result.output
            is Output.Error -> Log.e("Error", "The $error and the ${result.exception}")
        }
        return output

    }
    private suspend fun<T : Any> apiOutput(call: suspend()-> Response<T>, error: String) : Output<T> {
        val response = call.invoke()
        return if (response.isSuccessful)
            Output.Success(response.body()!!)
        else
            Output.Error(IOException("OOps .. Something went wrong due to  $error"))
    }


    suspend fun<T : Any> processResponse(call: suspend()-> Response<T>) : RResponse<T> {
        return try {
            val response = call.invoke()
            return if (response.isSuccessful)
                RResponse.Success(response.body()!!)
            else
                RResponse.Error(response.errorBody())
        }
        catch (e: Throwable){
            val aResponse = Response.error<T>(
                403,
                "{\"message\":[${e.localizedMessage}]}"
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            RResponse.Error(aResponse.errorBody())
        }
    }
}