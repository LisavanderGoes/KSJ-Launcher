package com.lasa.ksj_launcher.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
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
    }
}

