package com.eatitdog.eatitdog.features.auth.register.fragment

import androidx.fragment.app.viewModels
import com.eatitdog.eatitdog.R
import com.eatitdog.eatitdog.base.BaseFragment
import com.eatitdog.eatitdog.databinding.FragmentRegisterBinding
import com.eatitdog.eatitdog.features.auth.register.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(R.layout.fragment_register) {
    override val viewModel: RegisterViewModel by viewModels()

    override fun start() {
    }

}