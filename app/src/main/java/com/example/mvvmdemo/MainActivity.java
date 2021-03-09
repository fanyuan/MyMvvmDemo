package com.example.mvvmdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mvvmdemo.databinding.ActivityMainBinding;
import com.example.mvvmdemo.viewmodel.QueryWeatherViewModel;

public class MainActivity extends AppCompatActivity {

    private QueryWeatherViewModel weatherViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        weatherViewModel = new QueryWeatherViewModel();
        binding.setViewModel(weatherViewModel);
    }
    public void test(View v){
        weatherViewModel.city.set("一个测试");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        weatherViewModel.cancelRequest();
    }
}