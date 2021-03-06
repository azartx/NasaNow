package com.solo4.nasanow.data.network.responses

import com.squareup.moshi.Json

data class ApodResponse(
    val copyright: String?,
    val date: String,
    val explanation: String,
    @Json(name = "hdurl") val hdUrl: String,
    @Json(name = "media_type") val mediaType: String,
    @Json(name = "service_version") val serviceVersion: String,
    val title: String,
    val url: String
)
