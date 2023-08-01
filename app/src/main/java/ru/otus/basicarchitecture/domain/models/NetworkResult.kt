package ru.otus.basicarchitecture.domain.models

sealed interface NetworkResult

class NetworkSuccess<D>(val networkData: D): NetworkResult

class NetworkError(val errorCode: Int, val errorMessage: String): NetworkResult

class NetworkException<E>(val exception: E ): NetworkResult
