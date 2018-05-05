package ru.demjanov_av.watch_weather;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;


import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //-------------Constants begin--------
    public static final String CITY_NAME ="cityName";
    private static final String LOG_TAG = "MainLog";
    //-------------Constants end----------

    //-------------Objects begin----------
    TextView temperText;
    TextView weatherText;
    TextView pressureText;
    TextView humidityText;

    private Handler handler = new Handler();
    //-------------Objects end------------

    //-------------MyObjects begin--------

    //-------------MyObjects end----------

    //------------Parameters begin--------
    static volatile boolean onUpdate = false;
    private String currentCityName = "";
    //------------Parameters end----------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return true;
    }

    @Override
    public void onDestroy(){ //---На всякий случай---
        handler.removeCallbacksAndMessages(null);

//        Intent intent = new Intent(getApplicationContext(), I_ServisWeatherRequest.class);
////        stopService(intent);

        super.onDestroy();
    }

    //////////////////////////////////////////////////////
    //  createElements method
    //////////////////////////////////////////////////////

    private void createElements(){
        //---TextView---
        temperText = (TextView)findViewById(R.id.textView);
        weatherText = (TextView)findViewById(R.id.textWeather);
        pressureText = (TextView)findViewById(R.id.textPressure);
        humidityText = (TextView)findViewById(R.id.textHumidity);

        //---Toolbar---
        Toolbar toolbar = (Toolbar) findViewById(R.id.menu);
        setSupportActionBar(toolbar);

        //---Navigation objects---
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_clouse);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //---MyObjects---
//        mcw = new MyCurrentWeather(); //FIXME
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


//    //////////////////////////////////////////////////////
//    //  renderWeather method
//    //////////////////////////////////////////////////////
//
//    public static synchronized void renderWeather(JSONObject json) {
//        mcw.activated = false;
//        try {
//            mcw.cytyName = (json.getString("name").toUpperCase(Locale.US) + ", "
//                    + json.getJSONObject("sys").getString("country"));
//
//            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
//            JSONObject main = json.getJSONObject("main");
//            mcw.weather = details.getString("description").toUpperCase(Locale.US);
//
//            mcw.humidity = main.getString("humidity") + "%";
//            mcw.pressure = main.getString("pressure") + " hPa";
//
//            mcw.temper = main.getDouble("temp"); //+ " ℃";
//
//            mcw.activated = true;
//        } catch (Exception e) {
//            Log.d(LOG_TAG, "One or more fields not found in the JSON data");//FIXME
//
//        }
//
//        onUpdate = true;
//    }
//
//
//    //////////////////////////////////////////////////////
//    //  onRequestWeatherData method
//    //////////////////////////////////////////////////////
//
//    public void onRequestWeatherData(String cityName){
//        this.currentCityName = cityName; //---Запоминаем город---
//
//        onUpdate = false;               //---Скидываем флаг---
//
//        //---Создаем интент---
//        Intent intent = new Intent(getApplicationContext(), I_ServisWeatherRequest.class);
//
//        //---Запускаем сервис и передаем в него город---
//        startService(intent.putExtra(CITY_NAME, cityName));
//
//        //---Запускаем задачу по ожиданию
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                long tm = System.currentTimeMillis() + 5000;
//                while (System.currentTimeMillis() != tm){
//                    if(onUpdate){ //---Если данные получены и распарсены, обновляемся и выходим
//                        updateWindow(mcw);
//                        return;
//                    }
//                }//---Если время истекло, ругаемся---
//                Toast.makeText(getApplicationContext(), R.string.place_not_found, Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//
//
//    //////////////////////////////////////////////////////
//    //  updateWindow method
//    //////////////////////////////////////////////////////
//
//    private void updateWindow(MyCurrentWeather mcw){
//        if(currentCityName.equals(mcw.cytyName)){
//            temperText.setText(String.format("%.2f",mcw.temper) + " ℃");
//            weatherText.setText(mcw.weather);
//            pressureText.setText(mcw.pressure);
//            humidityText.setText(mcw.humidity);
//        }
//    }
}
