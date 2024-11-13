package com.example.service_app;

import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner

class MyService : LifecycleService() {

    override fun onCreate() {
        super.onCreate()
        // Observing lifecycle events using LifecycleObserver
        ProcessLifecycleOwner.get().lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onStartEvent() {
                Log.d("MyService", "Service Started")
                // Do something when the service starts
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onStopEvent() {
                Log.d("MyService", "Service Stopped")
                // Do something when the service stops
            }
        })
    }

    override fun onBind(intent: Intent): IBinder? {
        if (intent != null) {
            super.onBind(intent)
        }
        // This service does not bind to any activity
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d("MyService", "onStartCommand")
        // Service will continue running even when app goes to the background
        return START_STICKY // Keep service running
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService", "Service Destroyed")
    }
}
