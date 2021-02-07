package com.example.weekweather.presentation.ui.forecastweather

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weekweather.R
import com.example.weekweather.application.WeatherApplication
import com.example.weekweather.databinding.WeatherForecastFragmentBinding
import com.example.weekweather.domain.entity.ForeCastResponse
import com.example.weekweather.presentation.ui.BaseFragment
import com.example.weekweather.presentation.ui.forecastweather.adapter.ForecastListAdapter
import javax.inject.Inject

class ForecastWeatherFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.weather_forecast_fragment

    private var forecastFragmentBinding: WeatherForecastFragmentBinding? = null

    private val weatherViewModel: ForecastWeatherViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var forecastWeekListAdapter: ForecastListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
    }

    override fun initialCondition() {
        showProgressScreen()
        weatherViewModel.loadForecastWeather()
    }

    private fun showProgressScreen() {
        forecastFragmentBinding?.forecastProgressScreen?.root?.visibility = View.VISIBLE
    }

    private fun hideProgressScreen() {
        forecastFragmentBinding?.forecastProgressScreen?.root?.visibility = View.GONE
    }

    override fun setUpViewModel() {
        weatherViewModel.forecastLiveData.observe(this@ForecastWeatherFragment, Observer { handleForecastResponse(it) })
    }

    private fun handleForecastResponse(foreCastResponse: ForeCastResponse) {
        try {
            setCurrentWeatherInfo(foreCastResponse)
            setUpForecastWeekList(foreCastResponse.daily)
        } finally {
            hideProgressScreen()
        }
    }

    private fun setUpForecastWeekList(list: List<ForeCastResponse.Daily>?) {
        forecastFragmentBinding?.weatherForecastRecyclerView?.run {
            layoutManager = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = getForecastListAdapterReady(list)
        }
    }

    private fun getForecastListAdapterReady(list: List<ForeCastResponse.Daily>?): ForecastListAdapter {
        return forecastWeekListAdapter.apply {
            items = list as MutableList<ForeCastResponse.Daily>
            actionListener = getForeCastListActionListener()
        }
    }

    private fun getForeCastListActionListener(): ForecastListAdapter.ActionListener {
        return object : ForecastListAdapter.ActionListener {
            override fun onDayClick(selectedDay: ForeCastResponse.Daily) {
                weatherViewModel.selectedForecastDayLiveData.value = selectedDay
                goToSelectedDay()
            }
        }
    }

    private fun goToSelectedDay() {
        findNavController().navigate(R.id.action_weatherForecastFragment_to_selectedForecastDayFragment)
    }

    private fun setCurrentWeatherInfo(foreCastResponse: ForeCastResponse) {
        forecastFragmentBinding?.run {
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
        val binding = WeatherForecastFragmentBinding.bind(view)
        forecastFragmentBinding = binding
        initialCondition()

    }

    override fun onDestroyView() {
        forecastFragmentBinding = null
        super.onDestroyView()
    }

    override fun onAttach(context: Context) {
        getComponentInjection(requireActivity())
        super.onAttach(context)
    }

    private fun getComponentInjection(context: Context) {
        (context.applicationContext as WeatherApplication)
            .weatherComponent.inject(this)
    }
}
