package com.stac.eatitdog.features.myinfo.fragment

import androidx.fragment.app.viewModels
import com.stac.eatitdog.R
import com.stac.eatitdog.base.BaseFragment
import com.stac.eatitdog.databinding.FragmentMyInfoBinding
import com.stac.eatitdog.features.myinfo.viewmodel.MyInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyInfoFragment : BaseFragment<FragmentMyInfoBinding, MyInfoViewModel>(R.layout.fragment_my_info) {
    override val viewModel: MyInfoViewModel by viewModels()

    override fun start() {
    }

}