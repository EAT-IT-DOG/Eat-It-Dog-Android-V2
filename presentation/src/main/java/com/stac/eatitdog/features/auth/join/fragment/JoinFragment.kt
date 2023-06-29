package com.stac.eatitdog.features.auth.join.fragment

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.stac.domain.request.auth.JoinRequest
import com.stac.eatitdog.R
import com.stac.eatitdog.base.BaseFragment
import com.stac.eatitdog.databinding.FragmentJoinBinding
import com.stac.eatitdog.extensions.repeatOnStarted
import com.stac.eatitdog.extensions.shortToast
import com.stac.eatitdog.features.auth.join.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.stac.eatitdog.features.auth.join.viewmodel.JoinViewModel.Event
import java.util.regex.Pattern

@AndroidEntryPoint
class JoinFragment : BaseFragment<FragmentJoinBinding, JoinViewModel>(R.layout.fragment_join) {
    override val viewModel: JoinViewModel by viewModels()

    override fun start() {
        repeatOnStarted {
            viewModel.eventFlow.collect() { event -> handleEvent(event) }
        }
        observeLiveData()
        collectJoinState()
    }

    private fun handleEvent(event: Event) = when(event) {
        is Event.OnClickLogin -> findNavController().navigate(R.id.main_login)
        is Event.OnClickJoin -> checkJoin()
        is Event.PasswordToggle -> changePwToggle(event.checked)
        is Event.PasswordCheckToggle -> changePwCheckToggle(event.checked)
    }

    private fun collectJoinState() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                joinState.collect { state ->
                    if (state.isUpdate) {
                        findNavController().navigate(R.id.main_login)
                        shortToast("회원가입에 성공하셨습니다.")
                    } else if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    private fun checkJoin(){
        if(binding.nameEt.text.isNullOrBlank()) {
            binding.nameEt.requestFocus()
            Toast.makeText(requireContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else if(binding.emailEt.text.isNullOrBlank()) {
            binding.emailEt.requestFocus()
            Toast.makeText(requireContext(), "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else if(!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,20}$", binding.pwEt.text.toString())) {
            binding.nameEt.requestFocus()
            Toast.makeText(requireContext(), "비밀번호는 영문, 숫자, 특수문자를 포함한 8-20자여야 합니다.", Toast.LENGTH_SHORT).show()
        } else if(binding.pwEt.text.toString() != binding.pwCheckEt.text.toString()) {
            binding.pwCheckEt.requestFocus()
            Toast.makeText(requireContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
        } else if(false) {
            //TODO 이메일 인증이 안되었을 때
        } else {
            viewModel.join(JoinRequest(binding.emailEt.text.toString(), binding.nameEt.text.toString(), binding.pwEt.text.toString()))
        }
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
        pw.observe(this@JoinFragment) {
            if(pw.value.isNullOrBlank()) {
                binding.pwToggleBtn.visibility = View.GONE
                binding.pwEt.inputType = 0x00000081
            } else {
                binding.pwToggleBtn.visibility = View.VISIBLE
            }
        }
        pwCheck.observe(this@JoinFragment) {
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