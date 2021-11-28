package com.solo4.nasanow.ui.auth

import android.graphics.Bitmap
import com.solo4.nasanow.data.base.BaseViewModel
import com.solo4.nasanow.data.base.RequestState
import com.solo4.nasanow.utils.GlideImageUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val useCase: AuthUseCase
) : BaseViewModel() {

    private val _requestState: MutableSharedFlow<RequestState> = MutableSharedFlow()
    val requestState = _requestState.asSharedFlow()

    private val _newImageUrl = MutableSharedFlow<Bitmap>()
    val newImageUrl = _newImageUrl.asSharedFlow()

    fun getApodImage() {
        launchOnLifecycle {
            _requestState.emit(RequestState.InProgress("Update image"))
            val result = useCase.getApod()
            result.onSuccess { response ->
                uploadImage(response[0].url)
            }
            result.onFailure {
                _requestState.emit(RequestState.Failure("Failed to upload image"))
            }
        }
    }

    private fun uploadImage(imageUrl: String) {
        useCase.uploadImageAsBitmap(imageUrl, object : GlideImageUtil.ImageCallback {
                override fun onImageReceived(isSuccess: Boolean, image: Bitmap?) {
                    launchOnLifecycle {
                        if (isSuccess) {
                            image?.let {
                                _newImageUrl.emit(it)
                                _requestState.emit(RequestState.Success("Image upload success"))
                            } ?: _requestState.emit(RequestState.Failure("Failed to load an image"))
                        } else {
                            _requestState.emit(RequestState.Failure("Failed to load an image"))
                        }
                    }
                }
        })
    }
}
