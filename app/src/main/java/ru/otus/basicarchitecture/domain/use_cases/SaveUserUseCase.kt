package ru.otus.basicarchitecture.domain.use_cases

import ru.otus.basicarchitecture.domain.repositories.UserRepository

class SaveUserUseCase(private val userRepository: UserRepository) {

    fun saveUser(){
        userRepository.saveUser()
    }
}