package com.example.redstackapp.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.redstackapp.frg.*

class MyPagerAdapter (fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return HomeFragment()
            }
            1 -> {
                return CommerceFragment()
            }
        }
        return HomeFragment()
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}