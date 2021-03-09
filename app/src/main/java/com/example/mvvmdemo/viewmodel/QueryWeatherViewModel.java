package com.example.mvvmdemo.viewmodel;

import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.mvvmdemo.APIService.ApiService;
import com.example.mvvmdemo.model.WeatherData;
import com.example.mvvmdemo.model.WeatherInfo;
import com.example.mvvmdemo.net.QueryWeatherRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QueryWeatherViewModel {
    public final ObservableBoolean loading = new ObservableBoolean(false);
    public final ObservableBoolean loadingSuccess = new ObservableBoolean(false);
    public final ObservableBoolean loadingFailure = new ObservableBoolean(false);

    public final ObservableField<String> city = new ObservableField<>();

    public final ObservableField<String> cityId = new ObservableField<>();

    public final ObservableField<String> temp1 = new ObservableField<>();

    public final ObservableField<String> temp2 = new ObservableField<>();

    public final ObservableField<String> weather = new ObservableField<>();

    public final ObservableField<String> time = new ObservableField<>();

    private Call<WeatherData> mCall;

    public QueryWeatherViewModel() {}
    public void queryWeather() {
        loading.set(true);
        loadingSuccess.set(false);
        loadingFailure.set(false);

        mCall = ApiService.get().create(QueryWeatherRequest.class)
                .queryWeather();
        mCall.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                Log.d("ddebug","response.raw().toString() = " + response.raw().toString());
                if(response.body() == null || response.body().getWeatherInfo() == null){
                    Log.d("ddebug","response.body() == null = " + (response.body() == null) +  "   response.body().getWeatherInfo() == null = " + (response.body().getWeatherInfo() == null));
                    return;
                }
                WeatherInfo info = response.body().getWeatherInfo();
                city.set(info.getCity());
                cityId.set(info.getCityid());
                temp1.set(info.getTemp1());
                temp2.set(info.getTemp2());
                weather.set(info.getPtime());
                time.set(info.getPtime());

                loading.set(false);
                loadingSuccess.set(true);
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                if(call.isCanceled()){
                    Log.d("tag","call is canceled.");
                }else {
                    loading.set(false);
                    loadingFailure.set(true);
                }
            }
        });
    }

    public void cancelRequest() {
        if (mCall != null) {
            mCall.cancel();
        }
    }
}
