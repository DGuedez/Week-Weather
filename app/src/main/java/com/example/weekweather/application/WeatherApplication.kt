package com.example.weekweather.application

import android.app.Application
import com.example.weekweather.BuildConfig
import com.example.weekweather.domain.entity.APIServiceToken
import com.example.weekweather.presentation.di.component.DaggerWeatherComponent
import com.example.weekweather.presentation.di.component.WeatherComponent
import com.example.weekweather.presentation.di.module.ApplicationModule


class WeatherApplication : Application() {
    lateinit var weatherComponent: WeatherComponent

    override fun onCreate() {
        super.onCreate()
        weatherComponent = initDagger(this)
    }

    private fun initDagger(application: Application): WeatherComponent {
        return DaggerWeatherComponent.builder()
            .configContext(application)
            .apiServiceToken(APIServiceToken(BuildConfig.WEATHER_ACCESS_TOKEN))
            .build()
    }
}