package com.eatitdog.eatitdog.features.auth.login.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.eatitdog.eatitdog.R
import com.eatitdog.eatitdog.base.BaseFragment
import com.eatitdog.eatitdog.databinding.FragmentLoginBinding
import com.eatitdog.eatitdog.extensions.repeatOnStarted
import com.eatitdog.eatitdog.features.auth.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.eatitdog.eatitdog.features.auth.login.viewmodel.LoginViewModel.Event
import com.eatitdog.eatitdog.features.main.activity.MainActivity

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {
    override val viewModel: LoginViewModel by viewModels()

    override fun start() {
        repeatOnStarted {
            viewModel.eventFlow.collect() { event -> handleEvent(event) }
        }
        observeLiveData()
    }

    private fun handleEvent(event: Event) = when(event) {
        is Event.OnClickRegister -> findNavController().navigate(R.id.registerFragment)
        is Event.PasswordToggle -> changeToggle(event.checked)
    }

    private fun changeToggle(checked : Boolean) {
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

    private fun observeLiveData() = with(viewModel) {
        pw.observe(this@LoginFragment) {
            if(pw.value.isNullOrBlank()) {
                binding.pwToggleBtn.visibility = View.GONE
                binding.pwEt.inputType = 0x00000081
            } else {
                binding.pwToggleBtn.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        binding.emailEt.setText("")
        binding.pwEt.setText("")
        super.onResume()
    }
}