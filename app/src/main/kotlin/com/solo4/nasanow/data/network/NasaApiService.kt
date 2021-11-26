package com.solo4.nasanow.data.network

import retrofit2.http.GET

interface NasaApiService {

    // https://api.nasa.gov/planetary/apod
    @GET("planetary/apod")
    suspend fun getAstronomyPictureOfTheDay(
        apiKey: String,
        date //???
    ) /*: Response<User>*/
}