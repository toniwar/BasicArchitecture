package ru.otus.basicarchitecture.domain.use_cases

import ru.otus.basicarchitecture.domain.repositories.UserRepository

class DownloadUserProfileUseCase(private val userRepository: UserRepository) {

    fun downloadUserProfile() = userRepository.downloadUserProfile()
}