package com.example.weekweather.presentation.ui.selectedforecastday

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.weekweather.R
import com.example.weekweather.application.WeatherApplication
import com.example.weekweather.databinding.SelectedForecastDayFragmentBinding
import com.example.weekweather.domain.entity.ForeCastResponse
import com.example.weekweather.presentation.ui.BaseFragment
import com.example.weekweather.presentation.ui.forecastweather.ForecastWeatherViewModel

class SelectedForecastDayFragment : BaseFragment() {
    private val weatherViewModel: ForecastWeatherViewModel by viewModels { viewModelFactory }
    private var selectedDayBinding : SelectedForecastDayFragmentBinding? = null

    override fun layoutId(): Int = R.layout.selected_forecast_day_fragment
    override fun setUpViewModel() {
    weatherViewModel.selectedForecastDayLiveData.observe(this@SelectedForecastDayFragment, Observer {  setSelectedDateLayoutData(it)})
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedDayBinding = SelectedForecastDayFragmentBinding.bind(view)
        initialCondition()
    }

    override fun initialCondition() {
        weatherViewModel.selectedForecastDayLiveData.value?.let {
            setSelectedDateLayoutData(it)
        } ?: run {
            hideSelectedDayInfo()
            showNoAvailableInfoMessage()
        }
    }
    private fun setSelectedDateLayoutData(dayData: ForeCastResponse.Daily) {
        selectedDayBinding?.run{
            selectedDayDate.text = dayData.dt
            selectedDayWeatherDescription.text = dayData.weather?.get(0)?.description
            selectedDayMaxMinDescription.text = getString(R.string.max_min_description,dayData.temp?.max,dayData.temp?.min)
            selectedDayDtemp.text = dayData.temp?.morn
            selectedDayEtemp.text = dayData.temp?.eve
            selectedDayNtemp.text = dayData.temp?.night
            selectedDayDFeels.text = dayData.thermalSensation?.morn
            selectedDayEFeels.text = dayData.thermalSensation?.eve
            selectedDayNFeels.text = dayData.thermalSensation?.night
        }
    }

    private fun hideSelectedDayInfo() {
        selectedDayBinding?.selectedDayInfoContainer?.visibility = View.GONE
    }

    private fun showNoAvailableInfoMessage() {
      selectedDayBinding?.selectedDayNoDataContainer?.visibility = View.VISIBLE

    }

    override fun onAttach(context: Context) {
        (context.applicationContext as WeatherApplication)
            .weatherComponent.inject(this)
        super.onAttach(context)
    }

    override fun onDestroyView() {
        selectedDayBinding = null
        super.onDestroyView()
    }
}