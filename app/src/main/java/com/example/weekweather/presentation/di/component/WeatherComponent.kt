package com.example.weekweather.presentation.di.component

import android.app.Application
import com.example.weekweather.application.WeatherApplication
import com.example.weekweather.domain.entity.APIServiceToken
import com.example.weekweather.presentation.di.module.*
import com.example.weekweather.presentation.ui.BaseFragment
import com.example.weekweather.presentation.ui.forecastweather.ForecastWeatherFragment
import com.example.weekweather.presentation.ui.selectedforecastday.SelectedForecastDayFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        RetrofitNetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface WeatherComponent {
    fun inject(target: BaseFragment)
    fun inject(target: ForecastWeatherFragment)
    fun inject(target:SelectedForecastDayFragment)

    @Component.Builder
    interface Builder{
        fun build(): WeatherComponent

        @BindsInstance
        fun configContext(weatherApplication: Application): Builder

        @BindsInstance
        fun apiServiceToken(apiServiceToken: APIServiceToken): Builder

    }
}