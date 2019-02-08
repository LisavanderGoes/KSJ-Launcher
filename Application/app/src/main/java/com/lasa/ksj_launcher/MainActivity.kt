package com.lasa.ksj_launcher

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.lasa.ksj_launcher.View.HomeActivity
import android.support.v4.content.ContextCompat

//TODO: 1. apps inladen
//TODO: 1. home button
//TODO: 2. search bar
//TODO: 3. swipedrawer (google, contacten, games(komt eronder))
//TODO: Hidden apps

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)

        val w = window
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)


    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.main_activity)
//    }
}

