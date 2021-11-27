package com.solo4.nasanow.ui.auth

import android.graphics.Bitmap
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.solo4.nasanow.data.base.BaseViewModel
import com.solo4.nasanow.data.base.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository, private val glide: RequestManager) : BaseViewModel() {

    private val _requestState: MutableSharedFlow<RequestState> = MutableSharedFlow()
    val requestState = _requestState.asSharedFlow()

    private val _newImageUrl = MutableSharedFlow<Bitmap>()
    val newImageUrl = _newImageUrl.asSharedFlow()

    fun getApodImage() {
        launchOnLifecycle {
            _requestState.emit(RequestState.InProgress("Update image"))
            val result = repository.getApod()
            Log.e("ffff","received")
            result.onSuccess { response ->
                uploadImageAsBitmap(response[0].url)

                Log.e("ffff","success response")
            }

            result.onFailure {
                _requestState.emit(RequestState.Failure("Failed to upload image"))
                Log.e("ffff",it.stackTraceToString())
            }
        }
    }

    private fun uploadImageAsBitmap(imageUrl: String) {
        glide
            .asBitmap()
            .load(imageUrl)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    launchOnLifecycle {
                        Log.e("ffff","fiiled")
                        _requestState.emit(RequestState.Failure("Failed to load an image"))
                    }
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    launchOnLifecycle {
                        Log.e("ffff","success")
                        _requestState.emit(RequestState.Success("Image upload success"))
                        _newImageUrl.emit(resource)
                    }
                    return true
                }

            })
            .submit()
    }
}
