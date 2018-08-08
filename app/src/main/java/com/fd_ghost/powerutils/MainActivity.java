package com.fd_ghost.powerutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fd_ghost.powerutils.WakeUpUtils;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WakeUpUtils.init(getApplicationContext());
        Log.e("screen locked:", WakeUpUtils.isScreenLocked()+"");
        Log.e("cpu locked:", WakeUpUtils.isCPULocked()+"");
    }
}
