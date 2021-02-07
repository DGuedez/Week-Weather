package com.example.weekweather.data.newtork

import com.example.weekweather.domain.entity.ForeCastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface WeatherService {

    @GET("/data/2.5/onecall")
   suspend fun getForecast(@QueryMap  params: Map<String, String>): ForeCastResponse
}