package com.example.weekweather.presentation.ui.forecastweather

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weekweather.R

class ForecastWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = ForecastWeatherFragment()
    }

    private lateinit var weatherViewModel: ForecastWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_forecast_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        weatherViewModel = ViewModelProvider(this).get(ForecastWeatherViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
