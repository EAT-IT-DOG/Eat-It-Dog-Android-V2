package com.stac.eatitdog.features.auth.login.fragment

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.stac.eatitdog.R
import com.stac.eatitdog.base.BaseFragment
import com.stac.eatitdog.databinding.FragmentLoginBinding
import com.stac.eatitdog.extensions.repeatOnStarted
import com.stac.eatitdog.extensions.shortToast
import com.stac.eatitdog.features.auth.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.stac.eatitdog.features.auth.login.viewmodel.LoginViewModel.Event

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {
    override val viewModel: LoginViewModel by viewModels()

    override fun start() {
        repeatOnStarted {
            viewModel.eventFlow.collect() { event -> handleEvent(event) }
        }
        observeLiveData()
        collectLoginState()
    }

    private fun handleEvent(event: Event) = when(event) {
        is Event.OnClickJoin -> findNavController().navigate(R.id.registerFragment)
        is Event.PasswordToggle -> changeToggle(event.checked)
        is Event.OnClickLogin -> checkLogin()
    }
    private fun collectLoginState() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                loginState.collect { state ->
                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    } else if (state.isUpdate) {
                        findNavController().navigate(R.id.main_home)
                    }
                }
            }
        }
    }


    private fun checkLogin() {
        if(binding.emailEt.text.isNullOrBlank()) {
            binding.emailEt.requestFocus()
            Toast.makeText(requireContext(), "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else if(binding.pwEt.text.isNullOrBlank()) {
            binding.pwEt.requestFocus()
            Toast.makeText(requireContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.login(binding.emailEt.text.toString(), binding.pwEt.text.toString())
        }
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
                changeToggle(true)
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