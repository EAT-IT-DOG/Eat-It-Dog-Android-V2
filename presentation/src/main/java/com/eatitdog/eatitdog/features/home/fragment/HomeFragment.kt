package com.eatitdog.eatitdog.features.home.fragment

import android.content.Intent
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.eatitdog.eatitdog.R
import com.eatitdog.eatitdog.base.BaseFragment
import com.eatitdog.eatitdog.databinding.FragmentHomeBinding
import com.eatitdog.eatitdog.extensions.repeatOnStarted
import com.eatitdog.eatitdog.features.home.viewmodel.HomeViewModel
import com.eatitdog.eatitdog.features.home.viewmodel.HomeViewModel.Event
import com.eatitdog.eatitdog.features.main.activity.MainActivity
import com.eatitdog.eatitdog.features.result.activity.ResultActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModels()

    override fun start() {
        repeatOnStarted {
            viewModel.eventFlow.collect { event -> handleEvent(event) }
        }
        setListener()
    }

    private fun setListener() {
        binding.foodSearchEt.setOnKeyListener { v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                viewModel.onClickSearch()
            }
            false
        }
    }

    private fun handleEvent(event: Event) = when(event) {
        is Event.onClickSearch -> {
            val intent = Intent(activity, ResultActivity::class.java)
            intent.putExtra("name", binding.foodSearchEt.text.toString())
            startActivity(intent)
        }
    }

    override fun onResume() {
        binding.foodSearchEt.setText("")
        super.onResume()
    }

}