package com.eatitdog.eatitdog.features.auth.login.fragment

import androidx.fragment.app.viewModels
import com.eatitdog.eatitdog.R
import com.eatitdog.eatitdog.databinding.FragmentLoginBinding
import com.eatitdog.eatitdog.features.auth.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kr.co.moreversal.grabthathoe.base.BaseFragment

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {
    override val viewModel: LoginViewModel by viewModels()

    override fun start() {
    }

}