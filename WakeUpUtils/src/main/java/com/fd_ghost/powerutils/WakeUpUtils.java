package com.fd_ghost.powerutils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.view.WindowManager;



public class WakeUpUtils {
    private static PowerManager powerManager;
    private static PowerManager.WakeLock cpuLock;
    private static PowerManager.WakeLock screenLock;

    public static void init(Context context){
        powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
    }

    public static void keepCPU(){
        isInit();
        if(cpuLock==null) {
            cpuLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Wake CPU");
        }
        cpuLock.acquire();
    }

    public static void turnOnScreen(){
        isInit();
        if(screenLock==null) {
            screenLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Wake CPU");
        }
        screenLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE | PowerManager.ACQUIRE_CAUSES_WAKEUP, "Wake CPU");
        screenLock.acquire();
        screenLock.release();
    }

    public static void keepScreen(Activity activity){
        turnOnScreen();
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public static void releaseCPU(){
        if(cpuLock == null){
            throw new NullPointerException("CPU Lock dose not initialized");
        }
        cpuLock.release();
    }

    public static void releaseSreen(Activity activity){
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public static void releaseALL(Activity activity){
        releaseCPU();
        releaseSreen(activity);
    }

    public static boolean isCPULocked(){
        return cpuLock.isHeld();
    }

    public static boolean isScreenLocked(){
        if (Build.VERSION.SDK_INT < 20) {
            return powerManager.isScreenOn();
        } else {
            return powerManager.isInteractive();
        }
    }

    private static void isInit(){
        if(powerManager==null){
            throw new NullPointerException("WakeUpUtils dose not initialized");
        }
    }
}
