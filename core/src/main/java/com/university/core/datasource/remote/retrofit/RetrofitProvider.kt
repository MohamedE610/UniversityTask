package com.university.core.datasource.remote.retrofit

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.university.core.BuildConfig
import com.university.core.application.App
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val loggingInterceptor by lazy {
    HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }
}

private val networkConnectionInterceptor by lazy { NetworkConnectionInterceptor() }
private val chuckerInterceptor by lazy {
    ChuckerInterceptor.Builder(App.instance)
        .maxContentLength(250_000L)
        .build()
}

internal val okHttpClient by lazy {
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(chuckerInterceptor)
        .addInterceptor(networkConnectionInterceptor)
        .callTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .dispatcher(Dispatcher().apply { maxRequestsPerHost = 20 })
        .build()
}

val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()
}


inline fun <reified T : Any> buildApi(): T = retrofit.create(T::class.java)

private const val baseUrl = BuildConfig.BASE_URL
