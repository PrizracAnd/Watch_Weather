package ru.demjanov_av.watch_weather;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by demjanov on 24.04.2018.
 * Извиняюсь, не удержался, и передразнил в названии класса разработчиков Google)))
 */

public class I_ServisWeatherRequest extends IntentService{

    private final String TAG = "I_Servis_Log";
    private String cityName;


    //////////////////////////////////////////////////////
    //  Constructor
    //////////////////////////////////////////////////////

    public I_ServisWeatherRequest(){
        super("I_ServisWeatherRequest");
    }



    //////////////////////////////////////////////////////
    //  onHandleIntent override
    //////////////////////////////////////////////////////

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //---Get City Name---
        this.cityName = intent.getStringExtra(MainActivity.CITY_NAME);

        try {
            if (this.cityName != null) {
                //---Get JSON object
                JSONObject jsonObject = (new WeatherDataLoger().getJSONData(getApplicationContext(), this.cityName));

                //---Проверяем полученный JSON - если он НЕ пустой, парсим и кладем это дело в модель
                if (jsonObject != null){
                    MainActivity.renderWeather(jsonObject);
                }
            }
        } catch (Exception e){
            Log.d(TAG, e.getMessage().toString());
        }
    }

}
