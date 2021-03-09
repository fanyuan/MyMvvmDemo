package com.example.mvvmdemo.net;

import com.example.mvvmdemo.model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QueryWeatherRequest {
    @GET("data/cityinfo/101210101.html")
    Call<WeatherData> queryWeather();
}
