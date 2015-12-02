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
        /**
         * Initiate kidoz sdk with valid publisher id and security token
         */
        KidozSDK.initialize(this, "5", "i0tnrdwdtq0dm36cqcpg6uyuwupkj76s");
    }
}
