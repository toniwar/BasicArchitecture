package ru.otus.basicarchitecture.domain.use_cases

import ru.otus.basicarchitecture.domain.entities.User
import ru.otus.basicarchitecture.domain.repositories.UserRepository

class DeleteUserUseCase(private val userRepository: UserRepository) {

    fun deleteUser(user: User){
        userRepository.deleteUser(user)
    }
}