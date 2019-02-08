package com.lasa.ksj_launcher.Main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.lasa.ksj_launcher.Models.TabPage
import com.lasa.ksj_launcher.View.AllAppsDarkGridFragment
import com.lasa.ksj_launcher.View.AppsGridFragment

class AppTabsFragmentAdapter(
        fragmentManager: FragmentManager,
        private val mainContent: ArrayList<TabPage>,
        private val drawerContent: ArrayList<TabPage>,
        private val context: Context
): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return if (position < drawerContent.count()) {
            val dataSource = SelectiveAppListDataSource(drawerContent[position].apps)
            val adapter = PresentableAppsAdapter(context, dataSource)
            val fragment = AllAppsDarkGridFragment()
            fragment.pageTitle = drawerContent[position].name
            fragment.adapter = adapter
            fragment
        } else {
            val mainPosition = position - drawerContent.count()
            val dataSource = SelectiveAppListDataSource(mainContent[mainPosition].apps)
            val adapter = PresentableAppsAdapter(context, dataSource)
            val fragment = AppsGridFragment()
            fragment.adapter = adapter
            fragment
        }
    }

    override fun getCount(): Int {
        return mainContent.count() + drawerContent.count()
    }
}