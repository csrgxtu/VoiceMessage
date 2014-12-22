package com.csrgxtu.android.readtextfromsdcard;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String FILENAME = "hello_file";
        String string = "hello world!";

        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();
            Log.i(TAG, "hello_file created");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read text from file and display it
        File sdcard = Environment.getExternalStorageDirectory();
//        Log.i(TAG, sdcard.getAbsolutePath());
//        Log.i(TAG, sdcard.getPath());

        // Get the text file
        File file = new File(sdcard, "welcome.txt");

        // Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();

            Log.i(TAG, sdcard.getCanonicalPath());
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
            e.printStackTrace();
            Log.i(TAG, "Something Wrong");
        }

        //Find the view by its id
        TextView tv = (TextView)findViewById(R.id.text_view);

        //Set the text
        tv.setText(text);
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
}
