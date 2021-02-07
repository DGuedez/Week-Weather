package com.example.weekweather.presentation.di.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weekweather.presentation.factory.ViewModelFactory
import com.example.weekweather.presentation.util.annotation.ViewModelKey
import com.example.weekweather.presentation.ui.forecastweather.ForecastWeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {


    @Binds
    internal abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ForecastWeatherViewModel::class)
    internal abstract fun provideForeCastWeatherViewModel(forecastWeatherViewModel: ForecastWeatherViewModel): ViewModel
}