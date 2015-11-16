package com.kidoz.sdk.sample.app;

import android.app.Application;

import com.kidoz.sdk.api.KidozSDK;


/**
 * Created by KIDOZ.
 */
public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        KidozSDK.setLoggingEnabled(true);

        /** Initialize Kidoz SDK with correct publisher id and token (Publisher Id and Token provided by registering a new account via http://kidoz.net/
         * */
        KidozSDK.initialize(this, "5", "i0tnrdwdtq0dm36cqcpg6uyuwupkj76s");
    }
}
