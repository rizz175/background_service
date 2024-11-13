package com.example.service_app;

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.service_app.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Start the background service
        val serviceIntent = Intent(this, MyService::class.java)
        startService(serviceIntent)
        Log.d("MainActivity", "Service Started from MainActivity")
    }
    override fun onDestroy() {
        super.onDestroy()
        // Optionally, you can stop the service when the activity is destroyed
        val serviceIntent = Intent(this,  MyService::class.java)
        stopService(serviceIntent)
        Log.d("MainActivity", "Service Stopped from MainActivity")
    }
}