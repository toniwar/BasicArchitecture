package ru.otus.basicarchitecture.domain.usecases

import ru.otus.basicarchitecture.domain.models.UserName
import ru.otus.basicarchitecture.domain.models.UserAddress
import ru.otus.basicarchitecture.domain.models.UserAge
import ru.otus.basicarchitecture.domain.models.UserHobby
import ru.otus.basicarchitecture.domain.models.UserSurname
import ru.otus.basicarchitecture.domain.models.ViewModelData
import ru.otus.basicarchitecture.domain.repository.UserRepository

class SaveDataUseCase(private val userRepository: UserRepository) {

    fun setUserName(userName: ViewModelData){
        userRepository.saveUserName(userName as UserName)
    }

    fun setUserSurname(userSurname: UserSurname){
        userRepository.saveUserSurname(userSurname)
    }

    fun setUserAge(userAge: UserAge): Boolean{
        if(userAge.age >= 18) userRepository.saveUserAge(userAge)
        return userAge.age >= 18
    }

    fun setUserCountry(userAddress: UserAddress){
        userRepository.saveUserAddress(userAddress)
    }

    fun setUserHobby(userHobby: UserHobby){
       userRepository.saveUserHobby(userHobby)
    }


}