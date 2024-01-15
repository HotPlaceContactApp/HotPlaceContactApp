package com.example.hotplacecontactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.example.hotplacecontactapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

// 10조 맛집 연락처 앱 파이팅!!


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViewPager()
    }

    private fun initViewPager() {
        //ViewPager2 Adapter 셋팅
        var viewPager2Adatper = ViewPager2Adapter(this)
        viewPager2Adatper.addFragment(ContactListFragment())
        viewPager2Adatper.addFragment(MyPageFragment())

        //Adapter 연결
        binding.mainViewPager.apply {
            adapter = viewPager2Adatper

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
        }

        //ViewPager, TabLayout 연결
        TabLayoutMediator(binding.mainNavigationView, binding.mainViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Contact"
                1 -> tab.text = "My Page"
            }
        }.attach()
    }
}