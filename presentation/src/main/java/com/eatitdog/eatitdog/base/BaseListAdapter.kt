package kr.co.moreversal.grabthathoe.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T, B : ViewDataBinding>(
    @LayoutRes private val itemLayoutRes: Int,
    diffUtil: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseListAdapter<T, B>.BaseViewHolder>(diffUtil) {

    abstract fun action(item: T, binding: B)

    inner class BaseViewHolder(private val binding: B) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            action(item, binding)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}
