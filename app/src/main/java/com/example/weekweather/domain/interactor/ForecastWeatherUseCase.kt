package com.example.weekweather.domain.interactor

import com.example.weekweather.domain.IWeatherRepository
import com.example.weekweather.domain.entity.ForeCastResponse
import kotlinx.coroutines.*
import javax.inject.Inject

class ForecastWeatherUseCase @Inject constructor(private val weatherRepository: IWeatherRepository) {

    suspend fun invoke(coroutineScope: CoroutineScope): ForeCastResponse {
        val deferred = coroutineScope.async(Dispatchers.Main) {

            weatherRepository.getForecastInformation()
        }
        return deferred.await()
    }
}