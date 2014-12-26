package com.csrgxtu.android.callstatelistener;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.lang.reflect.Method;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        TelephonyMgr.listen(new TeleListener(), PhoneStateListener.LISTEN_CALL_STATE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class TeleListener extends PhoneStateListener {
        private static final String TAG = "TeleListener";
        private int ringCount = 0;

        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    Toast.makeText(getApplicationContext(), "CALL_STATE_IDLE", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "CALL_STATE_IDLE");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Toast.makeText(getApplicationContext(), "CALL_STATE_OFFHOOK", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "CALL_STATE_OOFHOOK");
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    //Toast.makeText(getApplicationContext(), incomingNumber, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "CALL_STATE_RINGING", Toast.LENGTH_SHORT).show();
                    ringCount++;
                    //Toast.makeText(getApplicationContext(), Integer.toString(ringCount), Toast.LENGTH_SHORT);
                    Log.i(TAG, "CALL_STATE_RINGING");
//                    killCall();
                    break;
                default:
                    break;
            }
        }

        public boolean killCall() {
            try {
                // Get the boring old TelephonyManager
                TelephonyManager telephonyManager =
                        (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

                // Get the getITelephony() method
                Class classTelephony = Class.forName(telephonyManager.getClass().getName());
                Method methodGetITelephony = classTelephony.getDeclaredMethod("getITelephony");

                // Ignore that the method is supposed to be private
                methodGetITelephony.setAccessible(true);

                // Invoke getITelephony() to get the ITelephony interface
                Object telephonyInterface = methodGetITelephony.invoke(telephonyManager);

                // Get the endCall method from ITelephony
                Class telephonyInterfaceClass =
                        Class.forName(telephonyInterface.getClass().getName());
                Method methodEndCall = telephonyInterfaceClass.getDeclaredMethod("endCall");

                // Invoke endCall()
                methodEndCall.invoke(telephonyInterface);

            } catch (Exception ex) { // Many things can go wrong with reflection calls
                Log.d(TAG,"PhoneStateReceiver **" + ex.toString());
                return false;
            }
            return true;
        }
    }
}
