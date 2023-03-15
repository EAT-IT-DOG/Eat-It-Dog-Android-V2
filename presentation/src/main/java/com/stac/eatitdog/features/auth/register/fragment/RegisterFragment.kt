package com.stac.eatitdog.features.auth.register.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.stac.eatitdog.R
import com.stac.eatitdog.base.BaseFragment
import com.stac.eatitdog.databinding.FragmentRegisterBinding
import com.stac.eatitdog.extensions.repeatOnStarted
import com.stac.eatitdog.features.auth.register.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.stac.eatitdog.features.auth.register.viewmodel.RegisterViewModel.Event

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(R.layout.fragment_register) {
    override val viewModel: RegisterViewModel by viewModels()

    override fun start() {
        repeatOnStarted {
            viewModel.eventFlow.collect() { event -> handleEvent(event) }
        }
        observeLiveData()
    }

    private fun handleEvent(event: Event) = when(event) {
        is Event.OnClickLogin -> findNavController().navigate(R.id.main_login)
        is Event.PasswordToggle -> changePwToggle(event.checked)
        is Event.PasswordCheckToggle -> changePwCheckToggle(event.checked)

    }

    private fun changePwToggle(checked : Boolean) {
        if(checked) {
            binding.pwEt.inputType = 0x00000081 //가림
            binding.pwToggleBtn.setImageResource(R.drawable.eye_off_icon)
            binding.pwEt.setSelection(binding.pwEt.text.length)
        } else {
            binding.pwEt.inputType = 0x00000091 //보임
            binding.pwToggleBtn.setImageResource(R.drawable.eye_on_icon)
            binding.pwEt.setSelection(binding.pwEt.text.length)
        }
    }

    private fun changePwCheckToggle(checked : Boolean) {
        if(checked) {
            binding.pwCheckEt.inputType = 0x00000081 //가림
            binding.pwCheckToggleBtn.setImageResource(R.drawable.eye_off_icon)
            binding.pwCheckEt.setSelection(binding.pwCheckEt.text.length)
        } else {
            binding.pwCheckEt.inputType = 0x00000091 //보임
            binding.pwCheckToggleBtn.setImageResource(R.drawable.eye_on_icon)
            binding.pwCheckEt.setSelection(binding.pwCheckEt.text.length)
        }
    }

    private fun observeLiveData() = with(viewModel) {
        pw.observe(this@RegisterFragment) {
            if(pw.value.isNullOrBlank()) {
                binding.pwToggleBtn.visibility = View.GONE
                binding.pwEt.inputType = 0x00000081
            } else {
                binding.pwToggleBtn.visibility = View.VISIBLE
            }
        }
        pwCheck.observe(this@RegisterFragment) {
            if(pwCheck.value.isNullOrBlank()) {
                binding.pwCheckToggleBtn.visibility = View.GONE
                binding.pwCheckEt.inputType = 0x00000081
            } else {
                binding.pwCheckToggleBtn.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        binding.emailEt.setText("")
        binding.pwEt.setText("")
        binding.pwCheckEt.setText("")
        binding.nameEt.setText("")
        super.onResume()
    }

}