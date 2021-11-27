package com.solo4.nasanow.ui.auth

import com.solo4.nasanow.data.base.BaseRepository
import com.solo4.nasanow.data.network.NasaApiService
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiService: NasaApiService) : BaseRepository() {

    suspend fun getApod() = createCall {
        apiService.getAstronomyPictureOfTheDay(
            apiKey = "DEMO_KEY",
            date = "",
            conceptTags = true,
            hd = false,
            count = 1,
            startDate = "",
            endDate = "",
            thumbs = true
        )
    }

}