package com.example.weekweather.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForeCastResponse(
    @SerializedName("timezone") val timezone: String?,
    @SerializedName("current") val current: CurrentWeather?,
    @SerializedName("daily") val daily: List<Daily>?,

    ) : Parcelable {
    @Parcelize
    data class CurrentWeather(
        @SerializedName("dt") val dt: String?,
        @SerializedName("temp") val temp: String?,
        @SerializedName("feels_like") val feelsLike: String?,
        @SerializedName("pressure") val pressure: String?,
        @SerializedName("humidity") val humidity: String?,
        @SerializedName("clouds") val clouds: String?,
        @SerializedName("wind_speed") val windSpeed: String?,
        @SerializedName("weather") val weather: List<Weather>?
    ) : Parcelable

    @Parcelize
    data class Weather(
        @SerializedName("id") val id: String?,
        @SerializedName("main") val main: String?,
        @SerializedName("description") val description: String?,
        @SerializedName("icon") val icon: String?
    ) : Parcelable

    @Parcelize
    data class Daily(
        @SerializedName("dt") val dt: String?,
        @SerializedName("temp") val temp: Temp?,
        @SerializedName("feels_like") val thermalSensation: ThermalSensation?,
        @SerializedName("pressure") val pressure: String?,
        @SerializedName("humidity") val humidity: String?,
        @SerializedName("wind_speed") val wind_speed: String?,
        @SerializedName("weather") val weather: List<Weather>?
    ) : Parcelable {

        @Parcelize
        data class Temp(
            @SerializedName("day") val day: String?,
            @SerializedName("min") val min: String?,
            @SerializedName("max") val max: String?,
            @SerializedName("night") val night: String?,
            @SerializedName("eve") val eve: String?,
            @SerializedName("morn") val morn: String?,
        ) : Parcelable

        @Parcelize
        data class ThermalSensation(
            @SerializedName("day") val day: String?,
            @SerializedName("night") val night: String?,
            @SerializedName("eve") val eve: String?,
            @SerializedName("morn") val morn: String?,
        ) : Parcelable
    }
}
