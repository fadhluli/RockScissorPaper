package com.fadtech.rockscissorpaper.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.fadtech.rockscissorpaper.R
import com.fadtech.rockscissorpaper.data.constant.Constant
import com.fadtech.rockscissorpaper.databinding.ActivityMainBinding
import com.fadtech.rockscissorpaper.enum.PlayerShape
import com.fadtech.rockscissorpaper.preference.UserPreference
import com.fadtech.rockscissorpaper.ui.dialog.DialogFragment
import com.fadtech.rockscissorpaper.ui.dialog.DialogFragmentListener
import com.fadtech.rockscissorpaper.ui.menu.MenuActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity(), DialogFragmentListener {

    private lateinit var binding: ActivityMainBinding
    private var playMode: Int? = null
    private var player2Choice: Int? = null
    private var player1Choice: Int? = null
    private var flag: Int = -1
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        start()
        onResetClick()
        onCloseClick()
    }

    private fun start() {
        playMode = intent.getIntExtra(Constant.PLAY_MODE, 0)
        binding.tvUser.setText(UserPreference(this).userName)

        if (playMode == 0) {
            flag = 0
            binding.tvComp.setText(getString(R.string.text_enemy_player2))
            onPlayerOneClick()
            onPlayerTwoClick()
        } else {
            flag = 1
            binding.tvComp.setText(getString(R.string.text_enemy_cpu))
            onPlayerOneClick()
        }
    }

    private fun onResetClick() {
        binding.ivReset.setOnClickListener {
            resetGame()
        }
    }

    private fun onCloseClick() {
        binding.ivClose.setOnClickListener {
            val intent = Intent(this@MainActivity, MenuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun onPlayerOneClick() {
        var random = 0
        binding.ivRockUser.setOnClickListener {
            player1Choice = 0
            setBgUserShape(0)
            showToastFromPlayer1Choice(player1Choice!!)
            if (playMode != 0) {
                random = Random.nextInt(0, 3)
                startGame(player1Choice, random)
            }
        }
        binding.ivPaperUser.setOnClickListener {
            player1Choice = 1
            setBgUserShape(1)
            showToastFromPlayer1Choice(player1Choice!!)
            if (playMode != 0) {
                random = Random.nextInt(0, 3)
                startGame(player1Choice, random)
            }
        }
        binding.ivScissorUser.setOnClickListener {
            player1Choice = 2
            showToastFromPlayer1Choice(player1Choice!!)
            setBgUserShape(2)
            if (playMode != 0) {
                random = Random.nextInt(0, 3)
                startGame(player1Choice, random)
            }
        }
    }

    private fun onPlayerTwoClick() {
        binding.ivRockComp.setOnClickListener {
            player2Choice = 0
            startGame(player1Choice, player2Choice)
        }
        binding.ivPaperComp.setOnClickListener {
            player2Choice = 1
            startGame(player1Choice, player2Choice)
        }
        binding.ivScissorComp.setOnClickListener {
            player2Choice = 2
            startGame(player1Choice, player2Choice)
        }
    }

    private fun startGame(playerOneChoice: Int?, playerTwoChoice: Int?) {
        if (playerOneChoice != null) {
            if ((playerOneChoice.plus(1)).mod(3) == playerTwoChoice) {
                Log.d(TAG, "setClickEvent Computer won")
                binding.ivMiddle.setImageResource(R.drawable.ic_comp_win_state)
                DialogFragment(1, flag).show(supportFragmentManager, null)
            } else if (playerOneChoice.equals(playerTwoChoice)) {
                Log.d(TAG, "setClickEvent draw")
                binding.ivMiddle.setImageResource(R.drawable.ic_draw_state)
                DialogFragment(3, flag).show(supportFragmentManager, null)
            } else {
                Log.d(TAG, "setClickEvent User won")
                binding.ivMiddle.setImageResource(R.drawable.ic_user_win_state)
                DialogFragment(0, flag).show(supportFragmentManager, null)
            }
            setBgUserShape(playerOneChoice)
            if (playerTwoChoice != null) {
                setBgCompShape(playerTwoChoice)
                if (playMode != 0) {
                    showToastFromPlayer2Choice(getString(R.string.text_player_cpu), playerTwoChoice)
                } else {
                    showToastFromPlayer2Choice(
                        getString(R.string.text_player_player2),
                        playerTwoChoice
                    )
                }
            }
        } else {
            Toast.makeText(
                this,
                String.format(
                    getString(R.string.text_toast_choose_attention),
                    UserPreference(this).userName
                ),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    fun resetGame() {
        setBgUserShape(-1)
        setBgCompShape(-1)
        player1Choice = null
        player2Choice = null
        binding.ivMiddle.setImageResource(R.drawable.ic_versus)
    }

    private fun showToastFromPlayer1Choice(choice: Int) {
        if (choice == 0) {
            Toast.makeText(
                this,
                String.format(
                    getString(R.string.text_toast_choice),
                    UserPreference(this).userName,
                    getString(R.string.text_shape_rock)

                ), Toast.LENGTH_SHORT
            ).show()
        } else if (choice == 1) {
            Toast.makeText(
                this,
                String.format(
                    getString(R.string.text_toast_choice),
                    UserPreference(this).userName,
                    getString(R.string.text_shape_paper)
                ), Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this,
                String.format(
                    getString(R.string.text_toast_choice),
                    UserPreference(this).userName,
                    getString(R.string.text_shape_scissor)
                ), Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showToastFromPlayer2Choice(player: String, choice: Int) {
        if (choice == 0) {
            Toast.makeText(
                this,
                String.format(
                    getString(R.string.text_toast_choice),
                    player,
                    getString(R.string.text_shape_rock)
                ), Toast.LENGTH_SHORT
            ).show()
        } else if (choice == 1) {
            Toast.makeText(
                this,
                String.format(
                    getString(R.string.text_toast_choice),
                    player,
                    getString(R.string.text_shape_paper)
                ), Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this,
                String.format(
                    getString(R.string.text_toast_choice),
                    player,
                    getString(R.string.text_shape_scissor)
                ), Toast.LENGTH_SHORT
            ).show()
        }
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

    override fun onDialogDismiss() {
        resetGame()
    }
}