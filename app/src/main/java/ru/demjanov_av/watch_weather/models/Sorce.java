package ru.demjanov_av.watch_weather.models;

import android.support.annotation.Nullable;

/**
 * Created by demjanov on 28.04.2018.
 */

public class Sorce {
    public long id_db;
    public String name;
    public String connection;
    public int img;
    public boolean priority = false;

    public Sorce(){
        this.id_db = -1;
        this.img = -1;
    }

    public Sorce(String name, @Nullable String connection){
        this();
        this.name = name;
        this.connection = connection;
    }

    public Sorce(String name, @Nullable String connection, @Nullable String priority){
        this(name, connection);
        if(priority != null && priority.equals("Y")){
            this.priority = true;
        } else this.priority = false;
    }

    public Sorce(String name, @Nullable String connection, @Nullable String priority, int img){
        this.id_db = -1;
        this.img = img;
        this.name = name;
        this.connection = connection;
        if(priority != null && priority.equals("Y")){
            this.priority = true;
        } else this.priority = false;

    }

    public Sorce(String name, long id_db, @Nullable String connection, @Nullable String priority, int img){
        this.id_db = id_db;
        this.img = img;
        this.name = name;
        this.connection = connection;
        if(priority != null && priority.equals("Y")){
            this.priority = true;
        } else this.priority = false;

    }

    public String getPriorityStr(){
        if(this.priority){
            return "Y";
        } else {
            return "N";
        }
    }

}
