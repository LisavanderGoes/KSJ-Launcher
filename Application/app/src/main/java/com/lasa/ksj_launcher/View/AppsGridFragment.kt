package com.lasa.ksj_launcher.View

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lasa.ksj_launcher.Main.PresentableAppsAdapter
import com.lasa.ksj_launcher.R
import kotlinx.android.synthetic.main.full_sized_grid_view.view.*

class AppsGridFragment: Fragment() {

    lateinit var adapter: PresentableAppsAdapter
    lateinit var onClick: (position: Int) -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.full_sized_grid_view, container, false)

        view.gridview.adapter = adapter

        view.gridview.setOnItemClickListener({ _, _, position, _ ->
            onClick(position)
        })

        return view
    }

}

