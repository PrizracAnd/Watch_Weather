package ru.demjanov_av.watch_weather;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class MyWidgetServise extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyWidget(getApplicationContext(), intent);
    }
}
