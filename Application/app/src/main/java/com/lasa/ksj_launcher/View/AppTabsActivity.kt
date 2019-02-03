package com.lasa.ksj_launcher.View

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.lasa.ksj_launcher.Main.CompleteAppListDataSource
import com.lasa.ksj_launcher.Main.PresentableAppsAdapter
import com.lasa.ksj_launcher.Models.PresentableApp
import com.lasa.ksj_launcher.Models.TabPage
import com.lasa.ksj_launcher.R
import kotlinx.android.synthetic.main.app_tabs_activity.*

class AppTabsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_tabs_activity)

        val list = arrayListOf(
                TabPage("name2", arrayListOf(PresentableApp("", "", null))),
                TabPage("name1", arrayListOf(PresentableApp("", "", null)))
        )
        val fragmentAdapter = AppTabsFragmentAdapter(
                supportFragmentManager,
                list,
                this,
                packageManager
        )
        viewpager.adapter = fragmentAdapter
        tabs.setupWithViewPager(viewpager)
        viewpager.currentItem = list.count()-1
    }
}

class AppTabsFragmentAdapter(
        fragmentManager: FragmentManager,
        private val list: ArrayList<TabPage>,
        private val context: Context,
        private val packageManager: PackageManager
): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        val fragment = AppsGridFragment()
        val dataSource = CompleteAppListDataSource(packageManager)
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