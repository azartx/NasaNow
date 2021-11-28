package com.solo4.nasanow.utils

import android.graphics.Bitmap
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class GlideImageUtil(private val glide: RequestManager) {

    fun getImageFromRemote(
        imageUrl: String,
        imageCallback: ImageCallback,
        glideListener: RequestListener<Bitmap> = initGlideRequestListener(imageCallback)
    ) {
        glide
            .asBitmap()
            .load(imageUrl)
            .listener(glideListener)
            .submit()
    }

    private fun initGlideRequestListener(imageCallback: ImageCallback) = object : RequestListener<Bitmap> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Bitmap>?,
                isFirstResource: Boolean
            ): Boolean {
                imageCallback.onImageReceived(false, null)
                return false
            }

            override fun onResourceReady(
                resource: Bitmap?,
                model: Any?,
                target: Target<Bitmap>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                imageCallback.onImageReceived(true, resource)
                return true
            }
        }

    interface ImageCallback {
        fun onImageReceived(isSuccess: Boolean, image: Bitmap?)
    }
}
