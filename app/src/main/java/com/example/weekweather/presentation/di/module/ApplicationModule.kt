package com.example.weekweather.presentation.di.module

import android.app.Application
import android.content.Context
import com.example.weekweather.application.WeatherApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule() {

    @Singleton
    @Provides
    fun provideContext(weatherApplication: WeatherApplication): Context = weatherApplication
}