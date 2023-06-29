package com.stac.eatitdog.features.search.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.stac.domain.model.search.Category
import com.stac.domain.model.search.SearchResultByCategory
import com.stac.eatitdog.R
import com.stac.eatitdog.base.BaseFragment
import com.stac.eatitdog.databinding.FragmentSearchBinding
import com.stac.eatitdog.extensions.repeatOnStarted
import com.stac.eatitdog.extensions.shortToast
import com.stac.eatitdog.features.search.adapter.CategoryAdapter
import com.stac.eatitdog.features.search.adapter.CategorySearchAdapter
import com.stac.eatitdog.features.search.viewmodel.SearchViewModel
import com.stac.eatitdog.features.search.viewmodel.SearchViewModel.Event
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {
    override val viewModel: SearchViewModel by viewModels()

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categorySearchAdapter: CategorySearchAdapter

    private var args: String? = null

    var paging = false
    var judgment = ""

    override fun start() {
        repeatOnStarted {
            viewModel.eventFlow.collect { event -> handleEvent(event) }
        }
        setCategorySearchAdapter()
        setCategoryAdapter()
        collectResultState()
        setListener()
    }

    private fun setListener() {
        binding.foodSearchEt.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                viewModel.onClickSearch()
            }
            false
        }

        binding.rcvCategorySearch.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.rcvCategorySearch.canScrollVertically(1) && paging) {
                    if (judgment == "all") {
                        getResultAll()
                    } else if (judgment == "search") {
                        getResult(null)
                    } else if (judgment == "category") {
                        getResultByCategory()
                    }
                }
            }
        })
    }

    private fun handleEvent(event: Event) = when (event) {
        is Event.onClickSearch -> {
            getResult(0)
        }
    }

    private fun getResult(count: Int?) {
        val type = categoryAdapter.convertStringToType(categoryAdapter.currentCategory)
        val page = count ?: (categorySearchAdapter.itemCount / 10)
        val keyword = binding.foodSearchEt.text.toString()
        if(keyword.isNullOrBlank()){
            shortToast("검색어를 입력해주세요.")
        } else {
            viewModel.getResult(keyword, page, type)
        }
    }

    private fun getResultByCategory() {
        val type = categoryAdapter.convertStringToType(categoryAdapter.currentCategory)
        val page = (categorySearchAdapter.itemCount / 10)
        viewModel.getResultByCategory(page, type!!)
    }

    private fun getResultAll() {
        val page = categorySearchAdapter.itemCount / 10
        viewModel.getResultAll(page)
    }

    private fun setData() {
        args = arguments?.getString("category")
        categoryAdapter = CategoryAdapter(viewModel, args)
        if (args.isNullOrBlank()) {
            getResultAll()
        } else {
            viewModel.getResultByCategory(0, categoryAdapter.convertStringToType(args!!)!!)
        }
    }

    private fun setCategoryAdapter() {
        setData()
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

    private fun setCategorySearchAdapter() {
        categorySearchAdapter = CategorySearchAdapter()
        binding.rcvCategorySearch.adapter = categorySearchAdapter
    }

    private fun collectResultState() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                getResultState.collect { state ->
                    if (state.isUpdate) {
                        state.result?.let {
                            if (judgment == state.judgment && state.paging && state.page != 0) {
                                categorySearchAdapter.submitList((categorySearchAdapter.currentList + it).distinct())
                            } else {
                                categorySearchAdapter.submitList(null)
                                categorySearchAdapter.submitList(it)
                            }
                            judgment = state.judgment
                            paging = state.paging

                        }
                    }
                    if (state.error.isNotBlank()) {
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

