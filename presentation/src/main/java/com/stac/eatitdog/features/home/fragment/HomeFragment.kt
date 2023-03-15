package com.stac.eatitdog.features.home.fragment

import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.stac.eatitdog.R
import com.stac.eatitdog.base.BaseFragment
import com.stac.eatitdog.databinding.FragmentHomeBinding
import com.stac.eatitdog.extensions.repeatOnStarted
import com.stac.eatitdog.features.home.viewmodel.HomeViewModel
import com.stac.eatitdog.features.home.viewmodel.HomeViewModel.Event
import com.stac.eatitdog.features.main.activity.MainActivity
import com.stac.eatitdog.features.result.activity.ResultActivity
import com.stac.eatitdog.features.search.fragment.SearchFragment
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
        is Event.onClickMilkProduct -> {
            val bundle = bundleOf("category" to "유제품")
            findNavController().navigate(R.id.action_main_home_to_main_search, bundle)

        }
        is Event.onClickSnack -> {
            val bundle = bundleOf("category" to "간식")
            findNavController().navigate(R.id.action_main_home_to_main_search, bundle)
        }
        is Event.onClickMeat -> {
            val bundle = bundleOf("category" to "육류")
            findNavController().navigate(R.id.action_main_home_to_main_search, bundle)
        }
        is Event.onClickVegetable -> {
            val bundle = bundleOf("category" to "채소")
            findNavController().navigate(R.id.action_main_home_to_main_search, bundle)
        }
        is Event.onClickJunkFood -> {
            val bundle = bundleOf("category" to "인스턴트")
            findNavController().navigate(R.id.action_main_home_to_main_search, bundle)
        }
        is Event.onClickSeaFood -> {
            val bundle = bundleOf("category" to "해산물")
            findNavController().navigate(R.id.action_main_home_to_main_search, bundle)
        }
        is Event.onClickDrink -> {
            val bundle = bundleOf("category" to "음료")
            findNavController().navigate(R.id.action_main_home_to_main_search, bundle)
        }
        is Event.onClickSeaSoning -> {
            val bundle = bundleOf("category" to "조미료")
            findNavController().navigate(R.id.action_main_home_to_main_search, bundle)
        }
        is Event.onClickFruit -> {
            val bundle = bundleOf("category" to "과일")
            findNavController().navigate(R.id.action_main_home_to_main_search, bundle)
        }
    }

    override fun onResume() {
        binding.foodSearchEt.setText("")
        super.onResume()
    }



}