package ru.demjanov_av.watch_weather.models;

import android.support.annotation.Nullable;

/**
 * Created by demjanov on 28.04.2018.
 */

public class CityWeather {
    public long id_db;
    public String name;
    public long temper;
    public boolean istemper = false;
    public String weather;
    public String pressure;
    public String humidity;

    public CityWeather(){
        this.id_db = -1;
    }

    public CityWeather(String name, long temper, @Nullable String weather, @Nullable String pressure, @Nullable String humidity){
        this.id_db = -1;
        this.name = name;
        this.temper = temper;
        this.istemper = true;
        this.weather = weather;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public CityWeather(String name, long id_db, long temper, @Nullable String weather, @Nullable String pressure, @Nullable String humidity){
        this.id_db = id_db;
        this.name = name;
        this.temper = temper;
        this.istemper = true;
        this.weather = weather;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public void setTemper(long temper) {
        this.temper = temper;
        this.istemper = true;
    }
}
