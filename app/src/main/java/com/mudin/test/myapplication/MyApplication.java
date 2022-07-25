package com.mudin.test.myapplication;

import android.app.Application;
import android.content.Context;

import com.mudin.test.myapplication.di.AppComponent;
import com.mudin.test.myapplication.di.DaggerAppComponent;
import com.mudin.test.myapplication.di.UtilsModule;


//using ViewModel for reactive ui update
// This is the app aplication, the dagger component is defined at here

public class MyApplication extends Application {
    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder().utilsModule(new UtilsModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
