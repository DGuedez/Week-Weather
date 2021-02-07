package com.example.weekweather.data.repository

import com.example.weekweather.data.newtork.WeatherService
import com.example.weekweather.domain.IWeatherRepository
import com.example.weekweather.domain.entity.APIServiceToken
import com.example.weekweather.domain.entity.ForeCastResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val serviceToken: APIServiceToken
) : IWeatherRepository {

    override suspend fun getForecastInformation(latitude: String, longitude: String): ForeCastResponse {
        return withContext(Dispatchers.IO) {
            val request =
                mapOf(
                    "lat" to latitude,
                    "lon" to longitude,
                    "exclude" to "minutely,hourly,alerts",
                    "lang" to "es",
                    "units" to "metric",
                    "appid" to serviceToken.token
                )
            weatherService.getForecast(request)
        }
    }
}

