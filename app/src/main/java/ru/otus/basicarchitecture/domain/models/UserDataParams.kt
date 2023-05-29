package ru.otus.basicarchitecture.domain.models

class UserName(val name :String = "")

class UserSurname(val surname: String = "")

class UserAge(val age: Int = 0)

class UserHobby(val hobby: List<String> = listOf())

class UserAddress(
    val country: String = "",
    val city: String = "",
    val address: String = ""
)

