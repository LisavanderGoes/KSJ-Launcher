package com.lasa.ksj_launcher

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lasa.ksj_launcher.View.AppTabsActivity
import android.view.WindowManager



class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, AppTabsActivity::class.java)
        startActivity(intent)

        val w = window
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    }
}

