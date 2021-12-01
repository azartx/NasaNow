package com.solo4.nasanow.ui.auth

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.solo4.nasanow.data.base.BaseFragment
import com.solo4.nasanow.databinding.AuthFragmentBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.solo4.nasanow.R
import com.solo4.nasanow.data.base.RequestState
import com.solo4.nasanow.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment @Inject constructor() : BaseFragment(R.layout.auth_fragment) {

    override val views by viewBinding(AuthFragmentBinding::bind)
    override val viewModel: AuthViewModel by viewModels()

    init { receive() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views.authBackground.setOnClickListener {
            viewModel.getApodImage()
        }
    }

    private fun receive() {
        lifecycleScope.launchWhenStarted {
            launch {
                viewModel.requestState.collectLatest(::requestStateReceived)
            }
            launch {
                viewModel.newImageUrl.collectLatest(::newImageReceived)
            }
        }
    }

    private fun requestStateReceived(state: RequestState) {
        when(state) {
            is RequestState.InProgress -> {
                views.progressAuth.visibility = View.VISIBLE
                showToast(state.message)
            }
            is RequestState.Success -> {
                views.progressAuth.visibility = View.GONE
                showToast(state.message)                    }
            is RequestState.Failure -> {
                views.progressAuth.visibility = View.GONE
                showToast(state.message)                    }
        }
    }

    private fun newImageReceived(image: Bitmap) {
        views.authBackground.setImageBitmap(image)
    }
}
