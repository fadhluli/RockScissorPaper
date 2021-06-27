package com.fadtech.rockscissorpaper.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.bumptech.glide.Glide
import com.fadtech.rockscissorpaper.data.constant.Constant
import com.fadtech.rockscissorpaper.databinding.ActivitySplashScreenBinding
import com.fadtech.rockscissorpaper.ui.landingpage.LandingPageActivity

class SplashScreenActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        Glide.with(this)
            .load(Constant.SPLASH_SCREEN_IMG_URL)
            .centerCrop()
            .into(binding.ivSplashScreenTop)
        setEventSplash()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(timer != null){
            timer?.cancel()
            timer = null
        }
    }
    private fun setEventSplash() {
        timer = object :CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                val intent = Intent(this@SplashScreenActivity, LandingPageActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }

        }
        timer?.start()
    }
}