package ru.otus.basicarchitecture.domain.models

data class NetworkRequest(
    val count: Int = 1,
    val query: String
)