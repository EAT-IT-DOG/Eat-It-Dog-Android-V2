package com.eatitdog.eatitdog.features.home.fragment

import androidx.fragment.app.viewModels
import com.eatitdog.eatitdog.R
import com.eatitdog.eatitdog.base.BaseFragment
import com.eatitdog.eatitdog.databinding.FragmentHomeBinding
import com.eatitdog.eatitdog.features.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModels()

    override fun start() {

    }

}