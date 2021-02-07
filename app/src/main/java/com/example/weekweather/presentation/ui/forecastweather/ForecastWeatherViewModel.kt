package com.example.weekweather.presentation.ui.forecastweather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weekweather.domain.entity.ForeCastResponse
import com.example.weekweather.domain.interactor.ForecastWeatherUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForecastWeatherViewModel @Inject constructor(private val forecastUseCase: ForecastWeatherUseCase) :
    ViewModel() {

    var forecastLiveData = MutableLiveData<ForeCastResponse>()
    var forecast: ForeCastResponse?
        get() = forecastLiveData.value
        set(value) {
            forecastLiveData.value = value
        }


    val selectedForecastDayLiveData = MutableLiveData<ForeCastResponse.Daily>()
    var selectedForecastDay: ForeCastResponse.Daily?
        get() = selectedForecastDayLiveData.value
        set(value) {
            selectedForecastDayLiveData.value = value
        }


    fun loadForecastWeather() {
        viewModelScope.launch {
            try {
                forecastLiveData.value = (forecastUseCase.invoke(this))
            } catch (e: Exception) {
                Log.d("Exeption", e.stackTraceToString())
            }

        }
    }
}