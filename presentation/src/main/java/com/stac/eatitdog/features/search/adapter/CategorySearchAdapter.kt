package com.stac.eatitdog.features.search.adapter

import android.content.Intent
import androidx.core.content.ContextCompat
import com.stac.domain.model.search.SearchResultByCategory
import com.stac.eatitdog.R
import com.stac.eatitdog.base.BaseListAdapter
import com.stac.eatitdog.databinding.ItemCategorySearchBinding
import com.stac.eatitdog.features.result.activity.ResultActivity
import com.stac.eatitdog.features.search.adapter.callback.CategorySearchDiffUtilCallback

class CategorySearchAdapter() : BaseListAdapter<SearchResultByCategory, ItemCategorySearchBinding>(R.layout.item_category_search, CategorySearchDiffUtilCallback) {
    override fun action(item: SearchResultByCategory, binding: ItemCategorySearchBinding) {
        var newItem = SearchResultByCategory(item.name, "#"+
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
            val intent = Intent(binding.root.context, ResultActivity::class.java)
            intent.putExtra("name", item.name)
            binding.root.context.startActivity(intent)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

    }

}