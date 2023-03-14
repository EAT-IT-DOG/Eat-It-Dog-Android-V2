package com.eatitdog.eatitdog.features.proposal.fragment

import androidx.fragment.app.viewModels
import com.eatitdog.eatitdog.R
import com.eatitdog.eatitdog.base.BaseFragment
import com.eatitdog.eatitdog.databinding.FragmentProposalBinding
import com.eatitdog.eatitdog.features.proposal.viewmodel.ProposalViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProposalFragment : BaseFragment<FragmentProposalBinding, ProposalViewModel>(R.layout.fragment_proposal) {
    override val viewModel: ProposalViewModel by viewModels()

    override fun start() {
    }

}