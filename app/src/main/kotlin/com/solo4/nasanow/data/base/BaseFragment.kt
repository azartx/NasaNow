package com.solo4.nasanow.data.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.solo4.nasanow.utils.showToast
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    abstract val views: ViewBinding
    abstract val viewModel: BaseViewModel

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBaseReceivers()
    }

    fun clickListenerWithView(view: View, block: (View) -> Unit) {
        view.setOnClickListener(block)
    }

    fun clickListener(view: View, block: () -> Unit) {
        view.setOnClickListener { block.invoke() }
    }

    private fun initBaseReceivers() {
        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.requestState.collectLatest { state ->
                    when (state) {
                        is RequestState.InProgress -> inProgressStateReceived(state)
                        is RequestState.Success -> successStateReceived(state)
                        is RequestState.Failure -> failureStateReceived(state)
                    }
                }
            }
        }
    }

    protected open fun inProgressStateReceived(state: RequestState.InProgress) {
        showToast(state.message)
    }

    protected open fun successStateReceived(state: RequestState.Success) {
        showToast(state.message)
    }

    protected open fun failureStateReceived(state: RequestState.Failure) {
        showToast(state.message)
    }
}
