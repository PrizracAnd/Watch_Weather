package ru.demjanov_av.watch_weather;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by demjanov on 09.04.2018.
 */

public class WeatherDataLoger {
    private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
    private static final String KEY = "x-api-key";

    private static final String RESPONSE = "cod";
    private static final String NEW_LINE = "\n";
    private static final int ALL_GOOD = 200;

    static JSONObject getJSONData(Context context, String city) {
        try {

            URL url = new URL(String.format(OPEN_WEATHER_MAP_API, city));

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.addRequestProperty(KEY, context.getString(R.string.open_weather_maps_app_id));

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder rawData = new StringBuilder(1024);
            String tempVariable;
            while ((tempVariable = reader.readLine()) != null) {
                rawData.append(tempVariable).append(NEW_LINE);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(rawData.toString());

            if (jsonObject.getInt(RESPONSE) != ALL_GOOD) {
                return null;
            }
            return jsonObject;
        } catch (Exception e) {
            return null; // FIXME Обработка ошибки
        }
    }

}
