package com.example.weekweather.presentation.di.module

import com.example.weekweather.data.newtork.WeatherService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module

class RetrofitNetworkModule {


    companion object {
        const val API_BASE_URL = "https://api.openweathermap.org"
    }


    @Provides
    fun provideRetroFitService(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): WeatherService {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loginInterceptor: HttpLoggingInterceptor): OkHttpClient {
        loginInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loginInterceptor)
            .build()
    }

    @Provides
    fun provideLoginInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()
}
