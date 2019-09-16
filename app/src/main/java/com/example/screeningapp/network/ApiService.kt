package com.example.screeningapp.network

import android.util.Log
import com.example.screeningapp.network.Config.API_KEY
import com.example.screeningapp.network.Config.API_PATH
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {
    private val interceptor = Interceptor { chain ->
        val url = chain.request().url.newBuilder().addQueryParameter("api_key", API_KEY).build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        Log.e("TAG Request",url.toString())
        val x = chain.proceed(request)

        Log.e("TAG Response",x.body.toString())

        x
    }

    private val apiClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().client(apiClient)
            .baseUrl(API_PATH)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val API: ApiInterface = getRetrofit().create(ApiInterface::class.java)
}