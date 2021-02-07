package com.example.weekweather.presentation.di.module

import com.example.weekweather.data.repository.WeatherRepository
import com.example.weekweather.domain.IWeatherRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(weatherRepository: WeatherRepository):IWeatherRepository
}