package com.eatitdog.eatitdog.features.search.adapter

import androidx.core.content.ContextCompat
import com.eatitdog.domain.model.search.SearchResult
import com.eatitdog.eatitdog.R
import com.eatitdog.eatitdog.base.BaseListAdapter
import com.eatitdog.eatitdog.databinding.ItemCategorySearchBinding
import com.eatitdog.eatitdog.features.search.adapter.callback.CategorySearchDiffUtilCallback

class CategorySearchAdapter() : BaseListAdapter<SearchResult, ItemCategorySearchBinding>(R.layout.item_category_search, CategorySearchDiffUtilCallback) {
    override fun action(item: SearchResult, binding: ItemCategorySearchBinding) {
        var newItem = SearchResult(item.name, "#"+
                when(item.category) {
                    "MILK_PRODUCT" -> "유제품"
                    "SNACK" -> "간식"
                    "MEAT" -> "육류"
                    "VEGETABLE" -> "채소"
                    "JUNK_FOOD" -> "인스턴트"
                    "SEAFOOD" -> "해산물"
                    "DRINK" -> "음료"
                    "SEASONING" -> "조미료"
                    else -> "과일"
                }
            , item.grade)

        binding.search = newItem
        binding.categorySearchLayout.background = when(item.grade) {
            "SAFE" -> {
                ContextCompat.getDrawable(binding.root.context, R.drawable.round_green)
            }
            "NORMAL" -> {
                ContextCompat.getDrawable(binding.root.context, R.drawable.round_yellow)
            }
            else -> {
                ContextCompat.getDrawable(binding.root.context, R.drawable.round_pink)
            }
        }


        binding.categorySearchLayout.setOnClickListener {
            //TODO 클릭 시 음식 상세 정보
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

    }

}