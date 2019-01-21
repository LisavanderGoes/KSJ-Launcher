package com.lasa.ksj_launcher.Main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.lasa.ksj_launcher.Models.PresentableApp
import com.lasa.ksj_launcher.R
import kotlinx.android.synthetic.main.presentable_apps_item.view.*

class PresentableAppsAdapter(
        private val context: Context,
        private val dataSource: PresentableAppsDataSource
) : BaseAdapter() {

    override fun getView(position: Int, covertView: View?, parent: ViewGroup?): View {
        val app = dataSource.getApp(position)

        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val appView = inflator.inflate(R.layout.presentable_apps_item, null)
        appView.icon.setImageDrawable(app?.icon)
        appView.name.text = app?.label

        return appView
    }

    override fun getItem(position: Int): PresentableApp? {
        return dataSource.getApp(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.getCount()
    }

}