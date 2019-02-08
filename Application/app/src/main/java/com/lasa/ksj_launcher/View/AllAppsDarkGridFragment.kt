package com.lasa.ksj_launcher.View

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.lasa.ksj_launcher.Main.PresentableAppsAdapter
import com.lasa.ksj_launcher.R
import kotlinx.android.synthetic.main.all_apps_grid_view.view.*

class AllAppsDarkGridFragment: Fragment() {

    lateinit var adapter: PresentableAppsAdapter
    lateinit var pageTitle: String
    lateinit var onClick: (position: Int) -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.all_apps_grid_view, container, false)

        view.gridview.adapter = adapter
        view.background = ColorDrawable(resources.getColor(R.color.transparantDarkColor))

        view.gridview.setOnItemClickListener({ _, _, position, _ ->
            onClick(position)
        })

        view.page_title.text = pageTitle

        val window = activity.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(activity, R.color.transparantDarkColor)

        return view
    }
}