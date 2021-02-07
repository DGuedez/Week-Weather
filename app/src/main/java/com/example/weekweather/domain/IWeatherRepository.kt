package com.example.weekweather.domain

import com.example.weekweather.domain.entity.ForeCastResponse

interface IWeatherRepository {

    @Throws(Exception::class)
    suspend fun getForecastInformation(latitude:String = "-33.4569", longitude: String = "-70.6483" ): ForeCastResponse
}