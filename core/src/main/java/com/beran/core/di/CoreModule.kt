package com.beran.core.di

import com.beran.core.BuildConfig
import com.beran.core.data.remote.retrofit.ApiService
import com.beran.core.data.repository.NewsRepository
import com.beran.core.domain.repository.INewsRepository
import com.beran.core.utils.Constants.API_KEY
import com.beran.core.utils.Constants.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val authInterceptor = Interceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder()
                .addHeader("X-Api-Key", API_KEY)
                .build()
            chain.proceed(newRequest)
        }
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single<INewsRepository> { NewsRepository(get()) }
}