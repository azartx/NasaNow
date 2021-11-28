package com.solo4.nasanow.data.di

import com.bumptech.glide.RequestManager
import com.solo4.nasanow.ui.auth.AuthRepository
import com.solo4.nasanow.ui.auth.AuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    fun provideAuthUseCase(repository: AuthRepository, glide: RequestManager): AuthUseCase {
        return AuthUseCase(repository, glide)
    }
}
