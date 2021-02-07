package com.example.weekweather.presentation.ui.forecastweather

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.weekweather.R
import com.example.weekweather.databinding.WeatherForecastFragmentBinding
import com.example.weekweather.domain.entity.ForeCastResponse
import com.example.weekweather.presentation.ui.BaseFragment

class ForecastWeatherFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.weather_forecast_fragment

    private var forecastFragmentBinding: WeatherForecastFragmentBinding? = null

    private val weatherViewModel: ForecastWeatherViewModel by viewModels { viewModelFactory }

    private lateinit var binding: WeatherForecastFragmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialCondition()
        setUpViewModel()
    }

    private fun initialCondition() {
        weatherViewModel.loadForecastWeather()
    }

    override fun setUpViewModel() {
        weatherViewModel.forecastLiveData.observe(this, Observer { handleForecastResponse(it) })
    }

    private fun handleForecastResponse(foreCastResponse: ForeCastResponse) {
        binding.run {
            setCurrentWeatherInfo(foreCastResponse)

        }
    }

    private fun setCurrentWeatherInfo(foreCastResponse: ForeCastResponse) {
        binding.run {
            weatherLocation.text = foreCastResponse.timezone
            weatherTemperature.text = getString(
                R.string.celsius,
                foreCastResponse.current?.temp
            )
            weatherFeelsLike.text = getString(
                R.string.feels_like,
                foreCastResponse.current?.feelsLike,
                foreCastResponse.current?.weather?.get(0)?.description
            )
            weatherWindValue.text = getString(
                R.string.wind_metric,
                foreCastResponse.current?.windSpeed
            )
            weatherPressureValue.text = getString(
                R.string.pressure_metric,
                foreCastResponse.current?.pressure
            )
            weatherHumidityValue.text = getString(
                R.string.humidity_metric,
                foreCastResponse.current?.humidity
            )
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = WeatherForecastFragmentBinding.bind(view)
        forecastFragmentBinding = binding
    }

    override fun onDestroyView() {
        forecastFragmentBinding = null
        super.onDestroyView()
    }
}
