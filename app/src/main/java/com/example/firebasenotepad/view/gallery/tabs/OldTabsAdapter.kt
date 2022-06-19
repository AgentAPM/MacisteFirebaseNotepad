package com.example.firebasenotepad.view.gallery.tabs

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.firebasenotepad.view.gallery.tabs.GalleryTab
import com.example.firebasenotepad.viewmodel.gallery.GalleryController

class OldTabsAdapter(
    private val tabs:List<GalleryTab>,
    private val tabTitles:List<Int>,
    private val tabControllers:List<GalleryController>,
    private val context: Context,
    fm: FragmentManager
):FragmentPagerAdapter(fm) {
    override fun getCount() = tabs.size

    override fun getPageTitle(position: Int): CharSequence {
        return context.getString(tabTitles[position])
    }

    override fun getItem(position: Int): Fragment {
        val tab = tabs[position]
        tab.controller = tabControllers[position]
        return tab
    }

}