package com.solo4.nasanow.data.network

import com.solo4.nasanow.data.network.responses.ApodResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {

    // https://api.nasa.gov/planetary/apod
    @GET("planetary/apod")
    suspend fun getAstronomyPictureOfTheDay(
        @Query("api_key") apiKey: String,
        @Query("date") date: String,
        @Query("concept_tags") conceptTags: Boolean,    // YYYY-MM-DD
        @Query("hd") hd: Boolean,
        @Query("count") count: Int,
        @Query("start_date") startDate: String,    // YYYY-MM-DD
        @Query("end_date") endDate: String,    // YYYY-MM-DD
        @Query("thumbs") thumbs: Boolean
    ): Array<ApodResponse>
}
