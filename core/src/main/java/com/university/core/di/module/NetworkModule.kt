package com.university.core.di.module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.university.core.BuildConfig
import com.university.core.datasource.remote.interceptor.NetworkConnectionInterceptor
import com.university.core.di.qualifier.ContextKey
import com.university.core.di.qualifier.ContextQualifier
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

private const val BASE_URL = "BASE_URL"

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named(BASE_URL)
    fun providesBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    @Singleton
    fun providesNetworkConnectionInterceptor(): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor()
    }

    @Provides
    @Singleton
    fun providesChuckerInterceptor(
        @ContextQualifier(ContextKey.APP) context: Context
    ): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .maxContentLength(250_000L)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkConnectionInterceptor: NetworkConnectionInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(networkConnectionInterceptor)
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .dispatcher(Dispatcher().apply { maxRequestsPerHost = 20 })
            .build()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRetrofitClient(
        @Named(BASE_URL) baseUrl: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(baseUrl)
            .build()
    }

}