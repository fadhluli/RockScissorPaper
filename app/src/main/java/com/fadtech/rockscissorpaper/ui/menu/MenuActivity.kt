package com.fadtech.rockscissorpaper.ui.menu

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fadtech.rockscissorpaper.R
import com.fadtech.rockscissorpaper.data.constant.Constant
import com.fadtech.rockscissorpaper.databinding.ActivityMenuBinding
import com.fadtech.rockscissorpaper.preference.UserPreference
import com.fadtech.rockscissorpaper.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTextMode()
        showSnackBar()
        onClick()
    }

    private fun showSnackBar() {
        val parentLayout: View = findViewById(android.R.id.content)
        Snackbar.make(
            parentLayout,
            String.format(
                getString(
                    R.string.text_welcome,
                    UserPreference(this).userName
                )
            ),
            Snackbar.LENGTH_LONG
        )
            .setActionTextColor(Color.CYAN)
            .setAction("Tutup") {
                Snackbar.LENGTH_INDEFINITE
            }.show()
        supportActionBar?.hide()
    }

    private fun setTextMode() {
        binding.tvUserVsPemain.text = String.format(
            getString(
                R.string.text_user_vs_user,
                UserPreference(this).userName
            )
        )
        binding.tvUserVsCpu.text = String.format(
            getString(
                R.string.text_user_vs_cpu,
                UserPreference(this).userName
            )
        )
    }

    private fun onClick() {
        binding.ivUserVsPemain.setOnClickListener {
            goToMainActivity(0)
        }
        binding.ivUserVsCpu.setOnClickListener {
            goToMainActivity(1)
        }
    }

    private fun goToMainActivity(playMode: Int) {
        val intent = Intent(this@MenuActivity, MainActivity::class.java).apply {
            putExtra(Constant.PLAY_MODE, playMode)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}