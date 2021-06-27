package com.fadtech.rockscissorpaper.ui.dialog

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.fadtech.rockscissorpaper.R
import com.fadtech.rockscissorpaper.databinding.FragmentDialogBinding
import com.fadtech.rockscissorpaper.preference.UserPreference
import com.fadtech.rockscissorpaper.ui.menu.MenuActivity

class DialogFragment(val value: Int, val flag: Int) : DialogFragment() {

    private lateinit var binding: FragmentDialogBinding
    private lateinit var userPreference: UserPreference
    private lateinit var listener: DialogFragmentListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogBinding.inflate(inflater, container, false)
        dialog?.setCancelable(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context.let {
            if (it != null) {
                userPreference = UserPreference(it)
            }
        }
        if (value == 0) {
            binding.tvPlayerName.text = String.format(
                getString(
                    R.string.text_winning_condition,
                    context.let {
                        userPreference.userName
                    }
                )
            )
        } else if (value == 1) {
            if(flag == 0){
                binding.tvPlayerName.text = String.format(
                    getString(
                        R.string.text_winning_condition,
                        "Player 2"
                    )
                )
            }else{
                binding.tvPlayerName.text = String.format(
                    getString(
                        R.string.text_winning_condition,
                        "CPU"
                    )
                )
            }
        } else {
            binding.tvPlayerName.setText(R.string.text_draw_condition)
        }
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.btnBackToMenu.setOnClickListener {
            val intent = Intent(view.context, MenuActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        binding.btnPlayAgain.setOnClickListener {
            dismiss()
            if(this::listener.isInitialized){
                listener.onDialogDismiss()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is DialogFragmentListener){
            listener = context
        }
    }
}