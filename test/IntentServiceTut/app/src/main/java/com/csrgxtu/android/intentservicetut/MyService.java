package com.csrgxtu.android.intentservicetut;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by archer on 12/22/14.
 */
public class MyService extends Service {
    private static final String TAG = "MyService";

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Service Started");

        int secs = intent.getIntExtra("TIME", 0);
        Log.i(TAG, "" + secs);

        try {
            Thread.sleep(5000);
            Toast.makeText(this, "Just slept 5 seconds", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Service Just Slept 5 Secs");
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Service Destroyed");
    }
}
