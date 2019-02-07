package com.lasa.ksj_launcher.Main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.lasa.ksj_launcher.Models.TabPage
import com.lasa.ksj_launcher.View.AppsGridFragment

class AppTabsFragmentAdapter(
        fragmentManager: FragmentManager,
        private val list: ArrayList<TabPage>,
        private val context: Context
): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        val fragment = AppsGridFragment()
        val dataSource = SelectiveAppListDataSource(list[position].apps)
        val adapter = PresentableAppsAdapter(context, dataSource)
        fragment.adapter = adapter
        return fragment
    }

    override fun getCount(): Int {
        return list.count()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].name
    }
}