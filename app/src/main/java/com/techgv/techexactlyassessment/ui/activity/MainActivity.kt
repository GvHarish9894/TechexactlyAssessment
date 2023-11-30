package com.techgv.techexactlyassessment.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.techgv.techexactlyassessment.ui.fragment.AppListFragment
import com.techgv.techexactlyassessment.ui.fragment.SettingsFragment
import com.techgv.techexactlyassessment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabLayout = binding.tabLayout
        viewPager = binding.pager

        initViewPager()

    }

    private fun initViewPager() {

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, listOf(AppListFragment(), SettingsFragment()))
        viewPager.adapter = adapter


        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Log.d("TAG", "onPageSelected: $position")
            }
        })

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Application"
                1 -> tab.text = "Settings"
            }
        }.attach()
    }
}