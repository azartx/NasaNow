package com.solo4.nasanow.data.di

import com.solo4.nasanow.ui.auth.AuthRepository
import com.solo4.nasanow.ui.auth.AuthViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelsModule {
    @Provides
    fun provideAuthViewModel(repository: AuthRepository) = AuthViewModel(repository)
}