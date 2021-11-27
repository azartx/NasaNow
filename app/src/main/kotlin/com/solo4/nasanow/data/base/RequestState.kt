package com.solo4.nasanow.data.base

sealed class RequestState {
    data class Success(val message: String): RequestState()
    data class Failure(val message: String): RequestState()
    data class InProgress(val message: String): RequestState()
}
