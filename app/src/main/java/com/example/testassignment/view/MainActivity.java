package com.example.testassignment.view;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.testassignment.controller.Utills.BroadcastService;
import com.example.testassignment.BuildConfig;
import com.example.testassignment.R;

public class MainActivity extends AppCompatActivity {
    NotificationManager notificationManager;
    NotificationCompat.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();

    }

    private void initview() {

        /*** Define Text view ****/
        TextView buildvariant_tv = findViewById(R.id.buildvariant_tv);
        /*** Get Build Variant and set to text View  ****/
        buildvariant_tv.setText(BuildConfig.BUILD_VARIANT);
    }


    @Override
    protected void onResume() {
        super.onResume();
        /*** Start Service ****/
        BroadcastService broadcastService = new BroadcastService();
        Log.d("tag", "onCreate: " + isMyServiceRunning(broadcastService.getClass()));
        if (!isMyServiceRunning(broadcastService.getClass())) {
            startService(new Intent(this, BroadcastService.class));
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        Log.d("Service status", "Running");
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("Service status", "Running");
                return true;
            }
        }
        Log.i("Service status", "Not running");
        return false;
    }
}
