package com.example.vlad.npng;

import android.app.Application;
import android.os.SystemClock;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vlad on 29.10.2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Don't do this! This is just so cold launches take some time
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));
    }
}
