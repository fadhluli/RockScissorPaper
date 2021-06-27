package com.fadtech.rockscissorpaper.ui.landingpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.fadtech.rockscissorpaper.databinding.ActivityLandingPageBinding
import com.fadtech.rockscissorpaper.ui.landingpage.adapter.ViewPagerAdapter
import com.fadtech.rockscissorpaper.ui.landingpage.fragment.LandingPageFragment1
import com.fadtech.rockscissorpaper.ui.landingpage.fragment.LandingPageFragment2
import com.fadtech.rockscissorpaper.ui.landingpage.fragment.LandingPageFragment3

class LandingPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandingPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        initViewPage()
        onPageChange()
    }

    private fun initViewPage() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(
            LandingPageFragment1()
        )
        viewPagerAdapter.addFragment(
            LandingPageFragment2()
        )
        viewPagerAdapter.addFragment(
            LandingPageFragment3()
        )
        binding.viewPager.apply {
            adapter = viewPagerAdapter
        }
        binding.indicator.setViewPager(binding.viewPager)
    }

    private fun onPageChange() {
        val viewPager = binding.viewPager
        val ivNextNavigate = binding.ivNextNavigate

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    ivNextNavigate.visibility = View.VISIBLE
                    onClick()
                } else {
                    ivNextNavigate.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun onClick() {
        binding.ivNextNavigate.setOnClickListener {
            val myFragment = supportFragmentManager.findFragmentByTag("f2")
            (myFragment as LandingPageFragment3).navigateToMenuGame()
        }
    }
}