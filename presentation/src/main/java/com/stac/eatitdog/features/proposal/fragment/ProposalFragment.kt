package com.stac.eatitdog.features.proposal.fragment

import androidx.fragment.app.viewModels
import com.stac.eatitdog.R
import com.stac.eatitdog.base.BaseFragment
import com.stac.eatitdog.databinding.FragmentProposalBinding
import com.stac.eatitdog.features.proposal.viewmodel.ProposalViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProposalFragment : BaseFragment<FragmentProposalBinding, ProposalViewModel>(R.layout.fragment_proposal) {
    override val viewModel: ProposalViewModel by viewModels()

    override fun start() {
    }

}