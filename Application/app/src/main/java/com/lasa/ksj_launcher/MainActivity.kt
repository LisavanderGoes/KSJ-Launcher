package com.lasa.ksj_launcher

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lasa.ksj_launcher.View.AppsListActivity

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, AppsListActivity::class.java)
        startActivity(intent)
    }
}

