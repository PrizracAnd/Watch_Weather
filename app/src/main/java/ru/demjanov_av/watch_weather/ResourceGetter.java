package ru.demjanov_av.watch_weather;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

public class ResourceGetter {
    private Context context;

    public ResourceGetter(Context context){
        this.context = context;
    }

    public String[] getCities(String strLocale){
        String[] cities = new String[0];

        Locale locale = new Locale(strLocale);
        Locale locale1 = Locale.getDefault();
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        Configuration validConf = new Configuration();
        configuration.setLocale(locale);
        validConf.setToDefaults();
        validConf.updateFrom(configuration);


        cities = context.getResources().getStringArray(R.array.cities);

        Locale.setDefault(locale1);
        configuration.setLocale(locale1);
        validConf.updateFrom(configuration);
        return cities;
    }

    public String[] getPressures(String strLocale){
        String[] pressures = new String[0];

        Locale locale = new Locale(strLocale);
        Locale locale1 = Locale.getDefault();
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        Configuration validConf = new Configuration();
        configuration.setLocale(locale);
        validConf.setToDefaults();
        validConf.updateFrom(configuration);


        pressures = context.getResources().getStringArray(R.array.pressures);

        Locale.setDefault(locale1);
        configuration.setLocale(locale1);
        validConf.updateFrom(configuration);
        return pressures;
    }

    public int getNumberCurrentPressure(){
        int l = -1;
        String[] pressures = context.getResources().getStringArray(R.array.pressures);
        String def = context.getResources().getString(R.string.default_pressure);

        for(int i = 0; i < pressures.length; i++){
            if(pressures[i].equals(def)) l = i;
        }

        return l;
    }

    public String getCurrentGradus(){
        return context.getResources().getString(R.string.default_gradus);
    }

}
