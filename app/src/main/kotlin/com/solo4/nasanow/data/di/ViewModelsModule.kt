package com.solo4.nasanow.data.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.solo4.nasanow.ui.auth.AuthRepository
import com.solo4.nasanow.ui.auth.AuthViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelsModule {
    @ViewModelScoped
    @Provides
    fun provideAuthViewModel(repository: AuthRepository, glide: RequestManager) = AuthViewModel(repository, glide)
}
