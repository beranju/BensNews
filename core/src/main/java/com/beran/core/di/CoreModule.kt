package com.beran.core.di

import androidx.room.Room
import com.beran.core.BuildConfig
import com.beran.core.data.local.room.NewDatabase
import com.beran.core.data.remote.retrofit.ApiService
import com.beran.core.data.repository.NewsRepository
import com.beran.core.domain.repository.INewsRepository
import com.beran.core.utils.Constants.API_HOST_NAME
import com.beran.core.utils.Constants.API_KEY
import com.beran.core.utils.Constants.API_KEY_HEADER
import com.beran.core.utils.Constants.BASE_URL
import com.beran.core.utils.Constants.DB_NAME
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<NewDatabase>().newDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            NewDatabase::class.java,
            DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        val certificatePinner = CertificatePinner.Builder()
            .add(API_HOST_NAME, "sha256/3zBmqXH8RxlLbD8WUZQIcgaUohZ0QxprXZrYvFGqWko=")
            .add(API_HOST_NAME, "sha256/hS5jJ4P+iQUErBkvoWBQOd1T7VOAYlOVegvv1iMzpxA=")
            .add(API_HOST_NAME, "sha256/A+L5NEZLzX9+Tzc2Y5TMTKjdRlaasLKndpTU0hrW6jI=")
            .add(API_HOST_NAME, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .build()

        val authInterceptor = Interceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder()
                .addHeader(API_KEY_HEADER, API_KEY)
                .build()
            chain.proceed(newRequest)
        }
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .certificatePinner(certificatePinner)
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
    single<INewsRepository> { NewsRepository(get(), get()) }
}