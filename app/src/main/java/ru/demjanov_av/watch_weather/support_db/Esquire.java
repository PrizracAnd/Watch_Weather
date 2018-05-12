package ru.demjanov_av.watch_weather.support_db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ru.demjanov_av.watch_weather.models.CityWeather;
import ru.demjanov_av.watch_weather.models.Measure;
import ru.demjanov_av.watch_weather.models.MySource;


/**
 * Created by Андрей on 17.04.2018.
 */

public class Esquire {
    private MyHelper myHelper;
    private SQLiteDatabase dataBase;

    ////////////////////////////////////////////
    //Methods addRecord
    ////////////////////////////////////////////
    //------------begin-------------------------
    public void addRecord(CityWeather cityWeather){
        ContentValues values = new ContentValues();
        values.put (MyHelper.COLUMN_CITY_NAME, cityWeather.name);
        values.put (MyHelper.COLUMN_WEATHER, cityWeather.weather);
        values.put (MyHelper.COLUMN_PRESSURE, cityWeather.pressure);
        values.put (MyHelper.COLUMN_HUMIDITY, cityWeather.humidity);
        values.put (MyHelper.COLUMN_TEMPER, cityWeather.temper);
        values.put (MyHelper.COLUMN_CITY_DATE, Calendar.getInstance().getTime().toString());
//        return dataBase.insert (MyHelper.TABLE_WEATHER, null, values);
        cityWeather.id_db = dataBase.insert(MyHelper.TABLE_CITIES, null, values);
    }


    public void addRecord(Measure measure){
        ContentValues values = new ContentValues();
        values.put (MyHelper.COLUMN_MEASURE_DOMAIN, measure.domain);
        values.put (MyHelper.COLUMN_MEASURE_NAME, measure.name);
        values.put (MyHelper.COLUMN_MEASURE_LNAME, measure.long_name);
        values.put (MyHelper.COLUMN_MEASURE_CHEK, measure.chek);
//        return dataBase.insert (MyHelper.TABLE_WEATHER, null, values);
        measure.id_db = dataBase.insert(MyHelper.TABLE_MEASURES, null, values);
    }

    public void addRecord(MySource source){
        ContentValues values = new ContentValues();
        values.put (MyHelper.COLUMN_SOURCE_NAME, source.name);
        values.put (MyHelper.COLUMN_SOURCE_IMAGE, source.name);
        values.put (MyHelper.COLUMN_CONNECTION, source.connection);
//        return dataBase.insert (MyHelper.TABLE_WEATHER, null, values);
        source.id_db = dataBase.insert(MyHelper.TABLE_SOURCES, null, values);
    }
    //------------end---------------------------


    ////////////////////////////////////////////
    //Methods editRecord
    ////////////////////////////////////////////
    //------------begin-------------------------
    public void editRecord(CityWeather cityWeather){
        ContentValues values = new ContentValues();
        values.put (MyHelper.COLUMN_CITY_ID, cityWeather.id_db);
        values.put (MyHelper.COLUMN_CITY_NAME, cityWeather.name);
        values.put (MyHelper.COLUMN_WEATHER, cityWeather.weather);
        values.put (MyHelper.COLUMN_PRESSURE, cityWeather.pressure);
        values.put (MyHelper.COLUMN_HUMIDITY, cityWeather.humidity);
        values.put (MyHelper.COLUMN_TEMPER, cityWeather.temper);
        values.put (MyHelper.COLUMN_CITY_DATE, Calendar.getInstance().getTime().toString());
        dataBase.update(MyHelper.TABLE_CITIES, values, MyHelper.COLUMN_CITY_ID + " = "+ cityWeather.id_db,null);
    }

    public void editRecord(Measure measure){
        ContentValues values = new ContentValues();
        values.put (MyHelper.COLUMN_MEASURE_ID, measure.id_db);
        values.put (MyHelper.COLUMN_MEASURE_DOMAIN, measure.domain);
        values.put (MyHelper.COLUMN_MEASURE_NAME, measure.name);
        values.put (MyHelper.COLUMN_MEASURE_LNAME, measure.long_name);
        values.put (MyHelper.COLUMN_MEASURE_CHEK, measure.chek);
        dataBase.update(MyHelper.TABLE_CITIES, values, MyHelper.COLUMN_MEASURE_ID + " = "+ measure.id_db,null);
    }

    public void editRecord(MySource source){
        ContentValues values = new ContentValues();
        values.put (MyHelper.COLUMN_SOURCE_ID, source.id_db);
        values.put (MyHelper.COLUMN_SOURCE_NAME, source.name);
        values.put (MyHelper.COLUMN_SOURCE_IMAGE, source.name);
        values.put (MyHelper.COLUMN_CONNECTION, source.connection);
        dataBase.update(MyHelper.TABLE_CITIES, values, MyHelper.COLUMN_SOURCE_ID + " = "+ source.id_db,null);
    }
    //------------end---------------------------


    ////////////////////////////////////////////
    //Methods delRecord and delAllRecord
    ////////////////////////////////////////////
    //------------begin-------------------------
    public void delRecord(String tableName, long id){
        switch (tableName){
            case MyHelper.TABLE_CITIES:
                dataBase.delete(MyHelper.TABLE_CITIES, MyHelper.COLUMN_CITY_ID + " = "+ id,null);
                break;
            case MyHelper.TABLE_MEASURES:
                dataBase.delete(MyHelper.TABLE_MEASURES, MyHelper.COLUMN_MEASURE_ID + " = "+ id,null);
                break;
            case MyHelper.TABLE_SOURCES:
                dataBase.delete(MyHelper.TABLE_SOURCES, MyHelper.COLUMN_SOURCE_ID + " = "+ id,null);
                break;
            default:
                break;
        }
    }

    public void delAllRecord(String tableName){
        switch (tableName){
            case MyHelper.TABLE_CITIES:
                dataBase.delete(MyHelper.TABLE_CITIES, null,null);
                break;
            case MyHelper.TABLE_MEASURES:
                dataBase.delete(MyHelper.TABLE_MEASURES, null,null);
                break;
            case MyHelper.TABLE_SOURCES:
                dataBase.delete(MyHelper.TABLE_SOURCES, null,null);
                break;
            default:
                break;
        }

    }
    //------------end---------------------------

    ////////////////////////////////////////////
    //Methods getAllRecord
    ////////////////////////////////////////////
    //------------begin-------------------------
    public List<CityWeather> getAllRecordsCities(){
        List<CityWeather> lmcw  = new ArrayList <CityWeather>();

        Cursor cursor = dataBase.query(MyHelper.TABLE_CITIES, myHelper.getAllColumn(MyHelper.TABLE_CITIES), null,null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            CityWeather cityWeather = new CityWeather(cursor.getString(1), cursor.getLong(0), cursor.getLong(2), cursor.getString(3) , cursor.getString(4), cursor.getString(5));
            lmcw.add(cityWeather);
            cursor.moveToNext();
        }

        cursor.close();
        return lmcw;
    }

    public List<Measure> getAllRecordsMeasures(){
        List<Measure> lm  = new ArrayList <Measure>();

        Cursor cursor = dataBase.query(MyHelper.TABLE_MEASURES, myHelper.getAllColumn(MyHelper.TABLE_MEASURES), null,null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Measure measure = new Measure(cursor.getString(2), cursor.getLong(0), cursor.getString(1), cursor.getString(3), cursor.getString(4));
            lm.add(measure);
            cursor.moveToNext();
        }

        cursor.close();
        return lm;
    }

    public List<MySource> getAllRecordsSources(){
        List<MySource> lms  = new ArrayList <MySource>();

        Cursor cursor = dataBase.query(MyHelper.TABLE_SOURCES, myHelper.getAllColumn(MyHelper.TABLE_SOURCES), null,null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            MySource mySource = new MySource(cursor.getString(1), cursor.getLong(0), cursor.getString(2), cursor.getString(4), (int)cursor.getLong(3));
            lms.add(mySource);
            cursor.moveToNext();
        }

        cursor.close();
        return lms;
    }
    //------------end---------------------------
}
