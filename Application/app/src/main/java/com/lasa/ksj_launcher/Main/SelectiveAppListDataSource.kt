package com.lasa.ksj_launcher.Main

import com.lasa.ksj_launcher.Main.Interface.PresentableAppsDataSource
import com.lasa.ksj_launcher.Models.PresentableApp

class SelectiveAppListDataSource(private val list: ArrayList<PresentableApp>): PresentableAppsDataSource {
    override fun getApp(position: Int): PresentableApp? {
        return list[position]
    }

    override fun getCount(): Int {
        return list.count()
    }
}