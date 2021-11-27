package com.solo4.nasanow.data.di

import com.solo4.nasanow.data.base.BaseNetwork
import com.solo4.nasanow.data.network.NasaApiService
import com.solo4.nasanow.ui.auth.AuthRepository
import com.solo4.nasanow.ui.auth.AuthViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideAuthRepository(apiService: NasaApiService) = AuthRepository(apiService)


}