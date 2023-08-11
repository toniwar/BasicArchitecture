package ru.otus.basicarchitecture.domain.use_cases

import ru.otus.basicarchitecture.domain.repositories.UserRepository

class DownloadAllUsersUseCase(private val userRepository: UserRepository) {

    fun downloadAllUsers() = userRepository.downloadAllUsers()
}