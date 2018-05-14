package com.example.ga_mlsdiscovery.viewmodelproject2.application;

import android.app.Application;
import com.example.ga_mlsdiscovery.viewmodelproject2.BuildConfig;
import timber.log.Timber;

public class MVVMapplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }
}
