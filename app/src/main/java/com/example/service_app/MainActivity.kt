package com.example.service_app;

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNav)
        bottomNavigationView.selectedItemId = R.id.nav_profile
        // Check if fragments are being loaded correctly
        if (savedInstanceState == null) {
            // Load the ProfileFragment by default when the app starts
            val profileFragment = ProfileFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, profileFragment)
                .commit()
        }

        // Set the listener for tab selections
        bottomNavigationView.setOnItemSelectedListener { item ->
            var fragment: Fragment? = null

            when (item.itemId) {
                R.id.nav_home -> fragment = HomeFragment()
                R.id.nav_search -> fragment = SearchFragment()
                R.id.nav_notifications -> fragment = NotifiactionsFragment()
                R.id.nav_profile -> fragment = ProfileFragment()
            }

            // Replace the fragment when a tab is selected
            if (fragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            }

            true
        }

        val serviceIntent = Intent(this, MyService::class.java)
        startService(serviceIntent)
        Log.d("MainActivity", "Service Started from MainActivity")
    }
    override fun onDestroy() {
        super.onDestroy()
        val serviceIntent = Intent(this,  MyService::class.java)
        stopService(serviceIntent)
        Log.d("MainActivity", "Service Stopped from MainActivity")
    }

    fun parseACL() {
        val result = mutableMapOf<String, Map<String, Int>>()

        val jsonString = """
        {
            "acl": {
                "player_level": "READ",
                "classification": {
                    "level": "FOOTBALL FOR FUN",
                    "strikes": [],
                    "freethrows": []
                },
                "write": {
                    "users": [
                        "fred"
                    ],
                    "groups": []
                },
                "read": {
                    "users": [],
                    "groups": [
                        "/AllPlayers"
                    ]
                },
                "none": {
                    "users": [],
                    "groups": []
                }
            }
        }
    """


        val jsonObject = JSONObject(jsonString)
        val acl = jsonObject.getJSONObject("acl")


        val keys = listOf("write", "read", "none")

        for (key in keys) {
            val keyObject = acl.getJSONObject(key)
            val users = keyObject.getJSONArray("users")
            val groups = keyObject.getJSONArray("groups")

            result[key] = mapOf(
                "users" to users.length(),
                "groups" to groups.length()
            )
        }

        result.forEach { (key, counts) ->
            println("$key: Users = ${counts["users"]}, Groups = ${counts["groups"]}")
        }
    }

}