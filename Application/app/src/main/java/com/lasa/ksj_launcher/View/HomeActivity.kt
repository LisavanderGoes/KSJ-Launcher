package com.lasa.ksj_launcher.View

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.lasa.ksj_launcher.Main.AppTabsFragmentAdapter
import com.lasa.ksj_launcher.Models.PresentableApp
import com.lasa.ksj_launcher.Models.TabPage
import com.lasa.ksj_launcher.R
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        val list = arrayListOf(
                TabPage("", loadAllApps()),
                TabPage("", loadAllApps())
        )

        val fragmentAdapter = AppTabsFragmentAdapter(
                supportFragmentManager,
                list,
                this
        )
        gesture_view.setOnTouchListener { view, event ->
            // ... Respond to touch events
            true
        }
        viewpager.adapter = fragmentAdapter
    }

    private fun loadAllApps(): ArrayList<PresentableApp> {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        val apps: ArrayList<PresentableApp> = arrayListOf()

        val availableActivities = packageManager.queryIntentActivities(intent, 0)
        for (availableActivity in availableActivities) {
            val app = PresentableApp(
                    availableActivity.activityInfo.packageName,
                    availableActivity.loadLabel(packageManager),
                    availableActivity.loadIcon(packageManager)
            )
            Log.e("tag", app.toString())
            apps.add(app)
        }
        return apps
    }
}
