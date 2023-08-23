package ru.otus.basicarchitecture.domain.entities

data class User(

    val name: String,
    val surname: String,
    val age: Int,
    val address: String? = null,
    val interests: Set<String> = setOf()

)
