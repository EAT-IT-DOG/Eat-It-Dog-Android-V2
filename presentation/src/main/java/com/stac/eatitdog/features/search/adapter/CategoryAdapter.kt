package com.stac.eatitdog.features.search.adapter

import android.util.Log
import androidx.core.content.ContextCompat
import com.stac.domain.model.search.Category
import com.stac.domain.model.search.CategoryType
import com.stac.eatitdog.R
import com.stac.eatitdog.base.BaseListAdapter
import com.stac.eatitdog.databinding.ItemCategoryBinding
import com.stac.eatitdog.features.search.adapter.callback.CategoryDiffUtilCallback
import com.stac.eatitdog.features.search.viewmodel.SearchViewModel

class CategoryAdapter(viewmodel: SearchViewModel, category: String?) : BaseListAdapter<Category, ItemCategoryBinding>(
    R.layout.item_category,
    CategoryDiffUtilCallback
) {
    private val clickCategory = category
    var currentCategory = ""
    private var beforeBinding: ItemCategoryBinding? = null
    private var vm = viewmodel

    override fun action(item: Category, binding: ItemCategoryBinding) {
        if(item.name == clickCategory) {
            currentCategory = item.name
            beforeBinding = binding
            changeLayout(item, binding, true)
        }else {
            if(currentCategory == item.name) {
                changeLayout(item, binding, true)
            } else {
                changeLayout(item, binding, false)
            }
        }
        binding.category = item

        binding.categoryLayout.setOnClickListener {
            if (currentCategory != item.name) {
                if (beforeBinding != null) {
                    changeLayout(Category(currentCategory), beforeBinding!!, false)
                }
                currentCategory = item.name
                beforeBinding = binding
                changeLayout(item, binding, true)
                vm.getResultByCategory(itemCount / 10, convertStringToType(item.name)!!)
            } else {
                changeLayout(item, beforeBinding!!, false)
                vm.getResultAll(0)
                currentCategory = ""
            }
        }
    }

    fun convertStringToType(str: String): CategoryType? {
        return when (str) {
            "유제품" -> CategoryType.MILK_PRODUCT
            "간식" -> CategoryType.SNACK
            "육류" -> CategoryType.MEAT
            "채소" -> CategoryType.VEGETABLE
            "인스턴트" -> CategoryType.JUNK_FOOD
            "해산물" -> CategoryType.SEAFOOD
            "음료" -> CategoryType.DRINK
            "조미료" -> CategoryType.SEASONING
            "과일" -> CategoryType.FRUIT
            else -> null
        }
    }

    private fun changeLayout(item: Category, binding: ItemCategoryBinding, isChecked: Boolean) {

        for (position in 0..8) {
            Log.d("getitem", getItem(position).name)
            if (item.name == getItem(position).name) {
                if (isChecked) {
                    when (position % 3) {
                        0 -> {
                            binding.categoryText.background = ContextCompat.getDrawable(binding.root.context, R.drawable.border_green)
                            binding.categoryText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.green))
                        }
                        1 -> {
                            binding.categoryText.background = ContextCompat.getDrawable(binding.root.context, R.drawable.border_yellow)
                            binding.categoryText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.yellow))
                        }
                        else -> {
                            binding.categoryText.background = ContextCompat.getDrawable(binding.root.context, R.drawable.border_pink)
                            binding.categoryText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.pink))
                        }
                    }

                } else {
                    binding.categoryText.background = when (position % 3) {
                        0 -> {
                            ContextCompat.getDrawable(binding.root.context, R.drawable.round_green)
                        }
                        1 -> {
                            ContextCompat.getDrawable(binding.root.context, R.drawable.round_yellow)
                        }
                        else -> {
                            ContextCompat.getDrawable(binding.root.context, R.drawable.round_pink)
                        }
                    }
                    binding.categoryText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                }
                break
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

    }

}