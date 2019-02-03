package com.lasa.ksj_launcher.Main.Interface

import com.lasa.ksj_launcher.Models.PresentableApp

interface PresentableAppsDataSource {
    fun getApp(position: Int): PresentableApp?
    fun getCount(): Int
}