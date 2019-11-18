package com.example.armutcasestudy.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import android.view.LayoutInflater
import android.widget.TextView
import com.example.armutcasestudy.fragment.FilterFragment
import com.example.armutcasestudy.R
import com.example.armutcasestudy.fragment.SearchFragment

class MainActivity : AppCompatActivity() {
    private var viewPager:ViewPager? = null
    private var tabLayout:TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewpager)
        createViewPager(viewPager!!)

        tabLayout = findViewById(R.id.tab_layout)
        tabLayout?.setupWithViewPager(viewPager)
        createTabIcons()

        tabLayout?.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.select()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }
    private fun createTabIcons() {

        val tabOne = LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as TextView
        tabOne.text = "Search"
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0,
            R.drawable.ic_search, 0, 0)
        tabLayout?.getTabAt(0)?.customView = tabOne

        val tabTwo = LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as TextView
        tabTwo.text = "Filter"
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0,
            R.drawable.ic_filter, 0, 0)
        tabLayout?.getTabAt(1)?.customView = tabTwo

    }
    private fun createViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(SearchFragment(), "Search")
        adapter.addFrag(FilterFragment(), "Filter")
        viewPager.adapter = adapter
    }
}
