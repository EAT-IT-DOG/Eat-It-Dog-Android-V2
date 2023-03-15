package com.stac.eatitdog.features.search.fragment

import android.content.Intent
import android.view.KeyEvent
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stac.domain.model.search.Category
import com.stac.domain.model.search.SearchResultByCategory
import com.stac.eatitdog.R
import com.stac.eatitdog.base.BaseFragment
import com.stac.eatitdog.databinding.FragmentSearchBinding
import com.stac.eatitdog.extension.shortToast
import com.stac.eatitdog.extensions.repeatOnStarted
import com.stac.eatitdog.features.home.fragment.HomeFragment
import com.stac.eatitdog.features.result.activity.ResultActivity
import com.stac.eatitdog.features.search.adapter.CategoryAdapter
import com.stac.eatitdog.features.search.adapter.CategorySearchAdapter
import com.stac.eatitdog.features.search.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.stac.eatitdog.features.search.viewmodel.SearchViewModel.Event

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {
    override val viewModel: SearchViewModel by viewModels()

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categorySearchAdapter: CategorySearchAdapter

    override fun start() {
        repeatOnStarted {
            viewModel.eventFlow.collect { event -> handleEvent(event) }
        }
        setCategoryAdapter()
        collectResultState()
        setListener()
    }

    private fun setListener() {
        binding.foodSearchEt.setOnKeyListener { v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
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

    private fun setCategoryAdapter() {
        val args = arguments?.getString("category")
        categoryAdapter = CategoryAdapter(viewModel, args)
        if(args.isNullOrBlank()) {
            viewModel.getResult()
        } else {
            viewModel.getResultByCategory(categoryAdapter.convertStringToType(args))
        }
        binding.rcvCategory.adapter = categoryAdapter
        categoryAdapter.submitList(
            listOf(
                Category("유제품"),
                Category("간식"),
                Category("육류"),
                Category("채소"),
                Category("인스턴트"),
                Category("해산물"),
                Category("음료"),
                Category("조미료"),
                Category("과일"),
            )
        )
    }

    private fun setCategorySearchAdapter(items: List<SearchResultByCategory>) {
        categorySearchAdapter = CategorySearchAdapter()
        binding.rcvCategorySearch.adapter = categorySearchAdapter
        binding.rcvCategorySearch.isNestedScrollingEnabled = false
        categorySearchAdapter.submitList(items)
    }

    private fun collectResultState() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                getResultByCategoryState.collect { state ->
                    if (state.isUpdate) {
                        state.result?.let {
                            setCategorySearchAdapter(it)
                        }
                    }
                    if (state.error.isNotBlank()) {
                        setCategorySearchAdapter(
                           listOf(
                               SearchResultByCategory(
                                   "값을 받아올 수 없습니다.",
                                   "값을 받아올 수 없습니다.",
                                   "값을 받아올 수 없습니다."
                               ),
                               SearchResultByCategory(
                                   "값을 받아올 수 없습니다.",
                                   "값을 받아올 수 없습니다.",
                                   "값을 받아올 수 없습니다."
                               ),
                               SearchResultByCategory(
                                   "값을 받아올 수 없습니다.",
                                   "값을 받아올 수 없습니다.",
                                   "값을 받아올 수 없습니다."
                               )

                           )
                        )
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    override fun onResume() {
        binding.foodSearchEt.setText("")
        super.onResume()
    }
}

