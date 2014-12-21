package com.csrgxtu.android.intentservicetut;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by archer on 12/21/14.
 */
public class RSSPullService extends IntentService {

    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Get data from incoming intent
        String dataString = workIntent.getDataString();

        // Do work here, based on the content of dataString

    }
}
