package com.example.weekweather.presentation.ui.forecastweather

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.weekweather.R
import com.example.weekweather.databinding.WeatherForecastFragmentBinding
import com.example.weekweather.presentation.ui.BaseFragment

class ForecastWeatherFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.weather_forecast_fragment


    private var forecastFragmentBinding: WeatherForecastFragmentBinding? = null

    private val weatherViewModel: ForecastWeatherViewModel by viewModels { viewModelFactory }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setUpViewModel()
    }

    override fun setUpViewModel() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = WeatherForecastFragmentBinding.bind(view)
        forecastFragmentBinding = binding
        binding.texto1.text = weatherViewModel.forecastText
    }

    override fun onDestroyView() {
        forecastFragmentBinding = null
        super.onDestroyView()
    }
}
