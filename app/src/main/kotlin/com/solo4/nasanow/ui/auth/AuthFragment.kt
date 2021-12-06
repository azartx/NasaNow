package com.solo4.nasanow.ui.auth

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.solo4.nasanow.data.base.BaseFragment
import com.solo4.nasanow.databinding.AuthFragmentBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.solo4.nasanow.R
import com.solo4.nasanow.data.base.RequestState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO("Отрефакторить скролл вью описания картинки")

@AndroidEntryPoint
class AuthFragment @Inject constructor() : BaseFragment(R.layout.auth_fragment) {

    override val views by viewBinding(AuthFragmentBinding::bind)
    override val viewModel: AuthViewModel by viewModels()

    init {
        initReceivers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        clickListener(views.imageviewButtonInfo, ::onButtonInfoClicked)
        clickListener(views.imageviewButtonCloseDescription, ::onButtonCloseDescriptionClicked)
        clickListener(views.imageviewButtonRefresh, ::onButtonRefreshClicked)
    }

    private fun onButtonRefreshClicked() {
        viewModel.getApodImage()
    }

    private fun onButtonCloseDescriptionClicked() {
        views.scrollviewDescription.visibility = View.GONE
    }

    private fun onButtonInfoClicked() {
        if (views.scrollviewDescription.visibility == View.GONE) {
            if (views.textviewImageDescription.text.isBlank()) {
                lifecycleScope.launch {
                    if (viewModel.imageDescription.last().isNotBlank()) {
                        views.textviewImageDescription.text = viewModel.imageDescription.last()
                        views.scrollviewDescription.visibility = View.VISIBLE
                    } else {
                        // TODO("show error")
                    }
                }
            } else {
                views.scrollviewDescription.visibility = View.VISIBLE
            }
        } else {
            views.scrollviewDescription.visibility = View.GONE
        }
    }

    private fun initReceivers() {
        lifecycleScope.launchWhenStarted {
            launch {
                viewModel.newImageUrl.collectLatest(::newImageReceived)
            }
            launch {
                viewModel.imageDescription.collectLatest(::imageDescriptionReceived)
            }
        }
    }

    private fun newImageReceived(image: Bitmap) {
        views.authBackground.setImageBitmap(image)
    }

    private fun imageDescriptionReceived(description: String) {
        views.scrollviewDescription.visibility = View.VISIBLE
        views.textviewImageDescription.text = description
    }

    override fun inProgressStateReceived(state: RequestState.InProgress) {
        super.inProgressStateReceived(state)
        views.progressAuth.visibility = View.VISIBLE
    }

    override fun successStateReceived(state: RequestState.Success) {
        super.successStateReceived(state)
        views.progressAuth.visibility = View.GONE
    }

    override fun failureStateReceived(state: RequestState.Failure) {
        super.failureStateReceived(state)
        views.progressAuth.visibility = View.GONE
    }
}
