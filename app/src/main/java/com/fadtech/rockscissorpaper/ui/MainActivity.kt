package com.fadtech.rockscissorpaper.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fadtech.rockscissorpaper.R
import com.fadtech.rockscissorpaper.databinding.ActivityMainBinding
import com.fadtech.rockscissorpaper.enum.PlayerShape
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setClickEvent()
    }

    private fun setClickEvent() {
        binding.ivRockUser.setOnClickListener {
            startGame(0, Random.nextInt(0, 3))
            Log.d(TAG, "setClickEvent user choice: Rock")
        }
        binding.ivPaperUser.setOnClickListener {
            startGame(1, Random.nextInt(0, 3))
            Log.d(TAG, "setClickEvent user choice: Papper")
        }
        binding.ivScissorUser.setOnClickListener {
            startGame(2, Random.nextInt(0, 3))
            Log.d(TAG, "setClickEvent user choice: Scissor")
        }
        binding.ivReset.setOnClickListener {
            resetGame()
        }
    }

    private fun startGame(userShape: Int, compShape: Int) {
        if ((userShape.plus(1)).mod(3) == compShape) {
            Log.d(TAG, "setClickEvent Computer won")
            binding.ivMiddle.setImageResource(R.drawable.ic_comp_win_state)
        } else if (userShape.equals(compShape)) {
            Log.d(TAG, "setClickEvent draw")
            binding.ivMiddle.setImageResource(R.drawable.ic_draw_state)
        } else {
            Log.d(TAG, "setClickEvent User won")
            binding.ivMiddle.setImageResource(R.drawable.ic_user_win_state)
        }
        setBgUserShape(userShape)
        setBgCompShape(compShape)
    }

    private fun resetGame() {
        setBgUserShape(-1)
        setBgCompShape(-1)
        binding.ivMiddle.setImageResource(R.drawable.ic_versus)
    }

    private fun setBgUserShape(userShape: Int) {
        when (PlayerShape.fromInt(userShape)) {
            PlayerShape.ROCK -> {
                binding.ivRockUser.setBackgroundResource(R.drawable.ic_select)
                binding.ivPaperUser.setBackgroundResource(0)
                binding.ivScissorUser.setBackgroundResource(0)
            }
            PlayerShape.PAPPER -> {
                binding.ivRockUser.setBackgroundResource(0)
                binding.ivPaperUser.setBackgroundResource(R.drawable.ic_select)
                binding.ivScissorUser.setBackgroundResource(0)
            }
            PlayerShape.SCISSOR -> {
                binding.ivRockUser.setBackgroundResource(0)
                binding.ivPaperUser.setBackgroundResource(0)
                binding.ivScissorUser.setBackgroundResource(R.drawable.ic_select)
            }
            else -> {
                binding.ivRockUser.setBackgroundResource(0)
                binding.ivPaperUser.setBackgroundResource(0)
                binding.ivScissorUser.setBackgroundResource(0)
            }
        }
    }

    private fun setBgCompShape(compShape: Int) {
        when (PlayerShape.fromInt(compShape)) {
            PlayerShape.ROCK -> {
                binding.ivRockComp.setBackgroundResource(R.drawable.ic_select)
                binding.ivPaperComp.setBackgroundResource(0)
                binding.ivScissorComp.setBackgroundResource(0)
            }
            PlayerShape.PAPPER -> {
                binding.ivRockComp.setBackgroundResource(0)
                binding.ivPaperComp.setBackgroundResource(R.drawable.ic_select)
                binding.ivScissorComp.setBackgroundResource(0)
            }
            PlayerShape.SCISSOR -> {
                binding.ivRockComp.setBackgroundResource(0)
                binding.ivPaperComp.setBackgroundResource(0)
                binding.ivScissorComp.setBackgroundResource(R.drawable.ic_select)
            }
            else -> {
                binding.ivRockComp.setBackgroundResource(0)
                binding.ivPaperComp.setBackgroundResource(0)
                binding.ivScissorComp.setBackgroundResource(0)
            }
        }
    }
}