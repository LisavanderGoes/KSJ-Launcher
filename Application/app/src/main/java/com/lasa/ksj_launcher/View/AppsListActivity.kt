package com.lasa.ksj_launcher.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lasa.ksj_launcher.Main.CompleteAppListDataSource
import com.lasa.ksj_launcher.Main.PresentableAppsAdapter
import com.lasa.ksj_launcher.R
import kotlinx.android.synthetic.main.full_sized_grid_view.*

class AppsListActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_sized_grid_view)

        val dataSource = CompleteAppListDataSource(packageManager)
        val adapter = PresentableAppsAdapter(this, dataSource)

        gridview.adapter = adapter

        gridview.setOnItemClickListener({ parent, view, position, id ->
            val app = dataSource.getApp(position)
            val i = packageManager.getLaunchIntentForPackage(app?.name.toString())
            startActivity(i)
        })
    }
}

