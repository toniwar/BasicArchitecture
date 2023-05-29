package ru.otus.basicarchitecture.domain.usecases

import ru.otus.basicarchitecture.domain.models.User
import ru.otus.basicarchitecture.domain.repository.UserRepository

class GetDataUseCase(private val userRepository: UserRepository) {
    fun getUser(): User {
        return userRepository.getData()
    }

}