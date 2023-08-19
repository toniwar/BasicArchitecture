package ru.otus.basicarchitecture.domain.use_cases

import ru.otus.basicarchitecture.domain.repositories.UserRepository
import javax.inject.Inject

class DownloadUserProfileUseCase @Inject constructor(private val userRepository: UserRepository) {

    fun downloadUserProfile() = userRepository.downloadUserProfile()
}