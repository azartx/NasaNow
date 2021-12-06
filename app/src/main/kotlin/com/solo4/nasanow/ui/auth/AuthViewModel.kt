package com.solo4.nasanow.ui.auth

import android.graphics.Bitmap
import android.util.Log
import com.solo4.nasanow.data.base.BaseViewModel
import com.solo4.nasanow.data.base.RequestState
import com.solo4.nasanow.utils.GlideImageUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val useCase: AuthUseCase
) : BaseViewModel() {

    private val _newImageUrl = MutableSharedFlow<Bitmap>()
    val newImageUrl = _newImageUrl.asSharedFlow()

    private val _imageDescription = MutableSharedFlow<String>()
    val imageDescription = _imageDescription.asSharedFlow()

    fun getApodImage() {
        launchOnLifecycle {
            _requestState.emit(RequestState.InProgress("Загружаю..."))
            val result = useCase.getApod()
            result.onSuccess { response ->
                _imageDescription.emit(response[0].explanation)
                uploadImage(response[0].url)
            }
            result.onFailure {
                _requestState.emit(RequestState.Failure("Ошибка, повторите!"))
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
                                _requestState.emit(RequestState.Success("Изображение успешно загружено"))
                            } ?: _requestState.emit(RequestState.Failure("Ошибка загрузки изображения"))
                        } else {
                            _requestState.emit(RequestState.Failure("Ошибка загрузки изображения"))
                        }
                    }
                }
        })
    }
}
