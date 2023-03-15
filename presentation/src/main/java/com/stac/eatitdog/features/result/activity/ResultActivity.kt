package com.stac.eatitdog.features.result.activity

import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.stac.domain.model.search.SearchResult
import com.stac.eatitdog.R
import com.stac.eatitdog.base.BaseActivity
import com.stac.eatitdog.databinding.ActivityResultBinding
import com.stac.eatitdog.extensions.repeatOnStarted
import com.stac.eatitdog.features.result.viewmodel.ResultViewModel.Event
import com.stac.eatitdog.features.result.viewmodel.ResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultActivity : BaseActivity<ActivityResultBinding, ResultViewModel>(R.layout.activity_result) {
    override val viewModel: ResultViewModel by viewModels()

    override fun start() {
        repeatOnStarted {
            viewModel.eventFlow.collect() { event -> handleEvent(event) }
        }
        collectResultState()
        getData()
    }

    private fun getData() {
        val name = intent.getStringExtra("name")
        if(name == null) {
            binding.searchLayout.visibility = View.GONE
            binding.noneText.visibility = View.VISIBLE
        } else {
            viewModel.getResultByName(name)
        }
    }

    private fun handleEvent(event: Event) = when(event) {
        is Event.onClickConfirm -> finish()
    }

    private fun collectResultState() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                getResultByNameState.collect { state ->
                    if (state.isUpdate) {
                        state.result?.let {
                            setData(it)
                        }
                    }
                    if (state.error.isNotBlank()) {
                        binding.searchLayout.visibility = View.GONE
                        binding.noneText.visibility = View.VISIBLE
                        binding.confirmBtn.setBackgroundResource(R.drawable.round_pink)
                    }
                }
            }
        }
    }

    private fun setData(data: SearchResult) {
        binding.foodName.text = data.name
        var text = ""
        if(data.benefit != null) text += data.benefit + "\n"
        if(data.symptom != null) text += data.symptom + "\n"
        if(data.caution != null) text += data.caution
        binding.explainText.text = text
        when(data.safeness) {
            "SAFE" -> {
                binding.edibleText.text = "섭취 가능합니다."
                binding.feedText.text = data.eatingMethod
                binding.explainTitle.text = "주성분 및 기능"
                binding.safenessImg.setImageResource(R.drawable.circle_green)
                binding.safenessText.text = "안전"
                binding.confirmBtn.setBackgroundResource(R.drawable.round_green)
            }
            "NORMAL" -> {
                binding.edibleText.text = "섭취 가능합니다."
                binding.feedText.text = data.eatingMethod
                binding.explainTitle.text = "증상"
                binding.safenessImg.setImageResource(R.drawable.circle_yellow)
                binding.safenessText.text = "보통"
                binding.confirmBtn.setBackgroundResource(R.drawable.round_yellow)
            }
            "DANGEROUS" -> {
                binding.edibleText.text = "먹으면 위험합니다. 만약 반려견이 섭취하였다면 신속히 병원 의료진과 상담하시고, 대처하시기 바랍니다."
                binding.feedLayout.visibility = View.GONE
                binding.explainTitle.text = "증상"
                binding.safenessImg.setImageResource(R.drawable.circle_pink)
                binding.safenessText.text = "위험"
                binding.confirmBtn.setBackgroundResource(R.drawable.round_pink)
            }
        }
    }
}