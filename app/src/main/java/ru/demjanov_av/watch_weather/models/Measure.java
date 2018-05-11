package ru.demjanov_av.watch_weather.models;

import android.support.annotation.Nullable;

/**
 * Created by demjanov on 28.04.2018.
 */

public class Measure {
    public static final String DOMAIN_TEMPER = "TEMPER";
    public static final String DOMAIN_PRESSURE = "PRESSURE";
    public static final String DOMAIN_HUMIDITY = "HUMIDITY";

    public long id_db;
    public String domain;
    public String name;
    public String long_name;
    public boolean chek = false;

    public Measure(){
        this.id_db = -1;
    }

    public Measure(String name, String domain, @Nullable String long_name){
        this.id_db = -1;
        this.name = name;
        this.domain = domain;
        this.long_name = long_name;
    }

    public Measure(String name, String domain, @Nullable String long_name, String chek){
        this(name, domain, long_name);
        if(chek != null && chek.equals("Y")) {
            this.chek = true;
        } else {
            this.chek = false;
        }
    }

    public Measure(String name, long id_db, String domain, @Nullable String long_name, String chek){
        this.id_db = id_db;
        this.name = name;
        this.domain = domain;
        this.long_name = long_name;
        if(chek != null && chek.equals("Y")) {
            this.chek = true;
        } else {
            this.chek = false;
        }
    }

    public String getChekStr(){
        if(this.chek){
            return "Y";
        }else {
            return "N";
        }
    }
}
