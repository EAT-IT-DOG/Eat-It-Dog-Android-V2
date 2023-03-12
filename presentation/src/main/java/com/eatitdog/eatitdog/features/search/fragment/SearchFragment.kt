package com.eatitdog.eatitdog.features.search.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.eatitdog.domain.model.search.Category
import com.eatitdog.domain.model.search.SearchResult
import com.eatitdog.eatitdog.R
import com.eatitdog.eatitdog.base.BaseFragment
import com.eatitdog.eatitdog.databinding.FragmentSearchBinding
import com.eatitdog.eatitdog.extension.shortToast
import com.eatitdog.eatitdog.features.search.adapter.CategoryAdapter
import com.eatitdog.eatitdog.features.search.adapter.CategorySearchAdapter
import com.eatitdog.eatitdog.features.search.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {
    override val viewModel: SearchViewModel by viewModels()

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categorySearchAdapter: CategorySearchAdapter

    override fun start() {
        setCategoryAdapter()
        collectResultState()
    }

    private fun setCategoryAdapter() {
        categoryAdapter = CategoryAdapter(viewModel)
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

    private fun setCategorySearchAdapter(items: List<SearchResult>) {
        categorySearchAdapter = CategorySearchAdapter()
        binding.rcvCategorySearch.adapter = categorySearchAdapter
        binding.rcvCategorySearch.isNestedScrollingEnabled = false
        categorySearchAdapter.submitList(items)
    }

    private fun collectResultState() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                getResultState.collect { state ->
                    if (state.isUpdate) {
                        state.result?.let {
                            setCategorySearchAdapter(it)
                        }
                    }
                    if (state.error.isNotBlank()) {
                        setCategorySearchAdapter(
                           listOf(
                               SearchResult(
                                   "값을 받아올 수 없습니다.",
                                   "값을 받아올 수 없습니다.",
                                   "값을 받아올 수 없습니다."
                               ),
                               SearchResult(
                                   "값을 받아올 수 없습니다.",
                                   "값을 받아올 수 없습니다.",
                                   "값을 받아올 수 없습니다."
                               ),
                               SearchResult(
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


}

