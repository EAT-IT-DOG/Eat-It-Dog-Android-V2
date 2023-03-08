package kr.co.moreversal.grabthathoe.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.eatitdog.eatitdog.BR

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutRes: Int
) : AppCompatActivity() {

    protected lateinit var binding: B
    protected abstract val viewModel: VM

    protected abstract fun start()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareDataBinding()
        start()
    }

    private fun prepareDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.setVariable(BR.vm, viewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::binding.isInitialized) binding.unbind()
    }
}
