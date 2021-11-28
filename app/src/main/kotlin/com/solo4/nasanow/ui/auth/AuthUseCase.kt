package com.solo4.nasanow.ui.auth

import android.graphics.Bitmap
import com.bumptech.glide.RequestManager
import com.solo4.nasanow.data.base.BaseUseCase
import com.solo4.nasanow.data.base.RequestState
import com.solo4.nasanow.data.network.responses.ApodResponse
import com.solo4.nasanow.utils.GlideImageUtil
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val glide: RequestManager
) : BaseUseCase() {

    suspend fun getApod(): Result<Array<ApodResponse>> {
        return repository.getApod()
    }

    fun uploadImageAsBitmap(imageUrl: String, imageListener: GlideImageUtil.ImageCallback) {
        GlideImageUtil(glide).getImageFromRemote(
            imageUrl = imageUrl,
            imageCallback = imageListener
        )
    }
}
