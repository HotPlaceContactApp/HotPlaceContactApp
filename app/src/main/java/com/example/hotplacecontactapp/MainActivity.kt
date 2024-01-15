package com.example.hotplacecontactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewpager2.widget.ViewPager2
import com.example.hotplacecontactapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

// 10조 맛집 연락처 앱 파이팅!!


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(binding.root)

        initViewPager()
    }

    private fun initViewPager() {
        val tabTextList = listOf("Contact", "My Page")

        //ViewPager2 Adapter 셋팅
        var viewPager2Adapter = ViewPager2Adapter(this)
        viewPager2Adapter.addFragment(ContactListFragment())
        viewPager2Adapter.addFragment(MyPageFragment())

        //Adapter 연결
        binding.mainViewPager.apply {
            adapter = viewPager2Adapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {})
        }

        //ViewPager, TabLayout 연결
        TabLayoutMediator(binding.mainNavigationView, binding.mainViewPager) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()
    }
}