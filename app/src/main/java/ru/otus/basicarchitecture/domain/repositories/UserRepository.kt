package ru.otus.basicarchitecture.domain.repositories

import ru.otus.basicarchitecture.domain.entities.User

interface UserRepository {

    fun editUserProfile(user: User)

    fun downloadUserProfile(): User

    fun saveUser()

    fun downloadAllUsers(): List<User>

    fun deleteUser(user: User)

}