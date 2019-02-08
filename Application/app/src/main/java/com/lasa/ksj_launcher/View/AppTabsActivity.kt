package com.lasa.ksj_launcher.View

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.lasa.ksj_launcher.Main.AppTabsFragmentAdapter
import com.lasa.ksj_launcher.Models.PresentableApp
import com.lasa.ksj_launcher.Models.TabPage
import com.lasa.ksj_launcher.R
import kotlinx.android.synthetic.main.app_tabs_activity.*

class AppTabsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_tabs_activity)

        val mainList = arrayListOf(
                TabPage("name2", arrayListOf(PresentableApp("", "", null))),
                TabPage("name1", arrayListOf(PresentableApp("", "", null)))
        )

        val drawerList = arrayListOf(
                TabPage("name2", arrayListOf(PresentableApp("", "", null))),
                TabPage("name1", arrayListOf(PresentableApp("", "", null)))
        )

        val fragmentAdapter = AppTabsFragmentAdapter(
                supportFragmentManager,
                mainList,
                drawerList,
                this
        )
        viewpager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewpager)
        viewpager.currentItem = drawerList.count()-1
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