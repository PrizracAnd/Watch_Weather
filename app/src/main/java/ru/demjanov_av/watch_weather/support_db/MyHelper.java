package ru.demjanov_av.watch_weather.support_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;

import ru.demjanov_av.watch_weather.R;
import ru.demjanov_av.watch_weather.ResourceGetter;
import ru.demjanov_av.watch_weather.models.Measure;

/**
 * Created by demjanov on 28.04.2018.
 */

public class MyHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String CONST_YES = "'Y'";
    public static final String CONST_NO = "'N'";

    //-----Data base parameters begin-----------
    private static final String DATABASE_NAME = "weather.db";   //--название бд
    public static final int DATABASE_VERSION = 1;               //--версия базы данных
    //-----Data base parameters end-------------


    //-----Tables specification begin-----------
    //-----Table cities begin-------------------
    public static final String TABLE_CITIES = "cities";                 //--имя таблицы (города)

    public static final String COLUMN_CITY_ID	 = "c_id";              //--primary key
    public static final String COLUMN_CITY_NAME	 = "c_name";            //--название города
    public static final String COLUMN_TEMPER	 = "c_temper";          //--температура
    public static final String COLUMN_WEATHER	 = "c_weather";         //--погода
    public static final String COLUMN_PRESSURE	 = "c_pressure";        //--давление
    public static final String COLUMN_HUMIDITY	 = "c_humidity";        //--влажность
    public static final String COLUMN_CITY_DATE	 = "c_date";            //--дата обращения
    //-----Table cities end---------------------

    //-----Table measures begin-----------------
    public static final String TABLE_MEASURES = "measures";             //--имя таблицы (ед. измерения)

    public static final String COLUMN_MEASURE_ID	 = "m_id";          //--primary key
    public static final String COLUMN_MEASURE_DOMAIN = "m_domain";       //--домен (категория)
    public static final String COLUMN_MEASURE_NAME	 = "m_name";        //--название
    public static final String COLUMN_MEASURE_LNAME	 = "m_long_name";   //--длинное название
    public static final String COLUMN_MEASURE_CHEK	 = "m_chek";        //--признак текущего выбора
    //-----Table measures end-------------------

    //-----Table sources begin------------------
    public static final String TABLE_SOURCES = "sources";                   //--имя таблицы (источники данных)

    public static final String COLUMN_SOURCE_ID 	    = "s_id";           //--primary key
    public static final String COLUMN_SOURCE_NAME	    = "s_name";         //--навание
    public static final String COLUMN_CONNECTION 	    = "s_connection";   //--строка запроса
    public static final String COLUMN_SOURCE_IMAGE      = "s_img";          //--код изображения
    public static final String COLUMN_SOURCE_PRIORITY	= "s_priority";     //--признак приорететности
    //-----Table sources end--------------------
    //-----Tables specification end-------------


    ////////////////////////////////////////////
    //Constructor of super
    ////////////////////////////////////////////
    public MyHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    ////////////////////////////////////////////
    //Method onCreate
    ////////////////////////////////////////////
    @Override
    public void onCreate(SQLiteDatabase db) {

        //-----Create table begin-----------------
        //-----Create table cities----------------
        db.execSQL("CREATE TABLE " + TABLE_CITIES + " (" +
                COLUMN_CITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_CITY_NAME + " TEXT, " +
                COLUMN_TEMPER + " INTEGER, " +
                COLUMN_WEATHER + " TEXT, " +
                COLUMN_PRESSURE + " TEXT, " +
                COLUMN_HUMIDITY + " TEXT, " +
                COLUMN_CITY_DATE + " DATE);"
        );


        //-----Create table measures--------------
        db.execSQL("CREATE TABLE " + TABLE_MEASURES + " (" +
                COLUMN_MEASURE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_MEASURE_DOMAIN + " TEXT, " +
                COLUMN_MEASURE_NAME + " TEXT, " +
                COLUMN_MEASURE_LNAME + " TEXT, " +
                COLUMN_MEASURE_CHEK + " TEXT);"
        );


        //-----Create table sources----------------
        db.execSQL("CREATE TABLE " + TABLE_SOURCES + " (" +
                COLUMN_SOURCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_SOURCE_NAME + " TEXT, " +
                COLUMN_CONNECTION + " TEXT, " +
                COLUMN_SOURCE_IMAGE + " TEXT, " +
                COLUMN_SOURCE_PRIORITY + " TEXT);"
        );
        //-----Create table end-------------------

        //-----Insert into table begin------------
        ResourceGetter rg = new ResourceGetter(this.context);

        //-----Insert into table cities-----------
        String[] cities = rg.getCities("en");
        for (int i = 0; i < cities.length; i++){
            db.execSQL("INSERT INTO " + TABLE_CITIES +
                    " (" + COLUMN_CITY_NAME + ", " +
                    COLUMN_CITY_DATE +
                    ") VALUES (" +
                    cities[i] + ", " +
                    Calendar.getInstance().getTime().toString() +
                    ");"
            );
        }

        //-----Insert into table measures-----------
        String chek = CONST_NO;
        if(rg.getCurrentGradus().equals("C")) chek = CONST_YES;
        db.execSQL("INSERT INTO " + TABLE_MEASURES +
                " (" + COLUMN_MEASURE_DOMAIN + ", " +
                COLUMN_MEASURE_NAME + ", " +
                COLUMN_MEASURE_LNAME + ", " +
                COLUMN_MEASURE_CHEK +
                ") VALUES ('" +
                Measure.DOMAIN_TEMPER + "', " +
                "'C'" + ", " +
                "'Celsius'" + ", " +
                chek +
                ");"
        );

        chek = CONST_NO;
        if(rg.getCurrentGradus().equals("F")) chek = CONST_YES;
        db.execSQL("INSERT INTO " + TABLE_MEASURES +
                " (" + COLUMN_MEASURE_DOMAIN + ", " +
                COLUMN_MEASURE_NAME +  ", " +
                COLUMN_MEASURE_LNAME +  ", " +
                COLUMN_MEASURE_CHEK +
                ") VALUES ('" +
                Measure.DOMAIN_TEMPER + "', " +
                "'F'" + ", " +
                "'Fahrenheit'" + ", " +
                chek +
                ");"
        );

        int numChek = rg.getNumberCurrentPressure();
//        String[] pressures = rg.getPressures("en");
        String[] pressures = context.getResources().getStringArray(R.array.pressures);
        for(int i = 0; i < pressures.length; i++){
            chek = CONST_NO;
            if(i == numChek) chek = CONST_YES;
            db.execSQL("INSERT INTO " + TABLE_MEASURES +
                    " (" + COLUMN_MEASURE_DOMAIN + ", " +
                    COLUMN_MEASURE_NAME + ", " +
//                    COLUMN_MEASURE_LNAME + ", " +
                    COLUMN_MEASURE_CHEK +
                    ") VALUES (" +
                    Measure.DOMAIN_PRESSURE + ", " +
                    pressures[i] + ", " +
//                    "" + ", " +
                    chek +
                    ");"
            );
        }

        db.execSQL("INSERT INTO " + TABLE_MEASURES +
                " (" + COLUMN_MEASURE_DOMAIN + ", " +
                COLUMN_MEASURE_NAME + ", " +
                COLUMN_MEASURE_LNAME + ", " +
                COLUMN_MEASURE_CHEK +
                ") VALUES ('" +
                Measure.DOMAIN_HUMIDITY + "', " +
                "'%'" + ", " +
                "'%'" + ", " +
                CONST_YES +
                ");"
        );

        //-----Insert into table sources-----------
        db.execSQL("INSERT INTO " + TABLE_SOURCES +
                " (" + COLUMN_SOURCE_NAME + ", " +
                COLUMN_CONNECTION +
//                COLUMN_SOURCE_IMAGE +
                COLUMN_SOURCE_PRIORITY +
                ") VALUES (" +
                "'Open Weather'" + ", " +
                "'http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric'" + ", " +
//                "'%'" + ", " +
                CONST_YES +
                ");"
        );
        //-----Insert into table end--------------
    }


    ////////////////////////////////////////////
    //Method onUpgrade
    ////////////////////////////////////////////
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL ("DROP TABLE IF EXISTS " + TABLE_CITIES);
        db.execSQL ("DROP TABLE IF EXISTS " + TABLE_MEASURES);
        db.execSQL ("DROP TABLE IF EXISTS " + TABLE_SOURCES);
        onCreate (db);

    }
}
