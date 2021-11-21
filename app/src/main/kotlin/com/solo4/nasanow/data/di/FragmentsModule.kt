package com.solo4.nasanow.data.di

import com.solo4.nasanow.ui.auth.AuthViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object FragmentsModule {
    @Provides
    fun provideAuthViewModel() = AuthViewModel()
}