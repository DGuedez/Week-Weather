package com.example.weekweather.data.newtork

import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherService {

    @GET("/data/2.5/onecall")
    fun getForecast(@QueryMap  params: Map<String, String>)
}