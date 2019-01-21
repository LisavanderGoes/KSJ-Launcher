package com.lasa.ksj_launcher.Main

import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import com.lasa.ksj_launcher.Models.PresentableApp

class CompleteAppListDataSource(private val manager: PackageManager): PresentableAppsDataSource {
    private var apps: ArrayList<PresentableApp>
    init {
        apps = arrayListOf()
        loadApps()
    }


    private fun loadApps() {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        val availableActivities = manager.queryIntentActivities(intent, 0)
        for (availableActivity in availableActivities) {
            val app = PresentableApp(
                    availableActivity.loadLabel(manager),
                    availableActivity.activityInfo.packageName,
                    availableActivity.loadIcon(manager)
            )
            Log.e("tag", app.toString())
            apps.add(app)
        }
    }

    fun getApps(): ArrayList<PresentableApp> {
        return apps
    }

    override fun getApp(position: Int): PresentableApp? {
        return apps[position]
    }

    override fun getCount(): Int {
        return apps.count()
    }

}
