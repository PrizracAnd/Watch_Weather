package ru.demjanov_av.watch_weather;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import ru.demjanov_av.watch_weather.models.CityWeather;

/**
 * Implementation of App Widget functionality.
 */
public class MyWidget extends AppWidgetProvider implements RemoteViewsService.RemoteViewsFactory {

    public static final String NO_DATA = "NOT DATA FOUND";
    private static CityWeather mcw;
    Context context;


    public MyWidget(Context context, Intent intent){
        this.context = context;
    }


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence cityText;
        if(mcw.name != null) {
            cityText = mcw.name;
        } else cityText = NO_DATA;

        CharSequence weatherText;
        if(mcw.weather != null){
            weatherText = mcw.weather;
        }else  weatherText = NO_DATA;
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
        views.setTextViewText(R.id.appwidget_textCity, cityText);
        views.setTextViewText(R.id.appwidget_textWeather, weatherText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }
//
//    @Override
//    public void onEnabled(Context context) {
//        // Enter relevant functionality for when the first widget is created
//    }
//
//    @Override
//    public void onDisabled(Context context) {
//        // Enter relevant functionality for when the last widget is disabled
//    }

    @Override
    public void onCreate() {
        mcw = new CityWeather();
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public RemoteViews getViewAt(int i) {
        return null;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}

