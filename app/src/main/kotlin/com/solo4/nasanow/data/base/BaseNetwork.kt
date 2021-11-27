package com.solo4.nasanow.data.base

import okio.IOException
import retrofit2.HttpException
import timber.log.Timber

abstract class BaseNetwork {
    suspend fun <T>createCall(apiCall: suspend () -> T): Result<T> {
        return try {
            Result.success(apiCall.invoke())
        } catch (throwable: Throwable) {
            Timber.e(
                "Cause is handled in BaseResult::call(catch block)\n"
                    .plus("api call method: $apiCall\n")
                    .plus(throwable.stackTraceToString())
            )
            when (throwable) {
                // Exception for an unexpected, non-2xx HTTP response
                is HttpException -> {
                    Result.failure(throwable)
                }
                // Exception for an internet connection
                is IOException -> {
                    Result.failure(throwable)
                }
                // Some other errors
                else -> {
                    Result.failure(throwable)
                }
            }
        }
    }
}
