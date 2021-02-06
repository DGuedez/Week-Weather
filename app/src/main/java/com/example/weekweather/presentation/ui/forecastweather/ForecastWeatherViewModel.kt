package com.example.weekweather.presentation.ui.forecastweather

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ForecastWeatherViewModel @Inject constructor() : ViewModel() {
    open val forecastText = "forecast text retrieve from viewmodel ;)"
}