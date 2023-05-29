package ru.otus.basicarchitecture.data

import ru.otus.basicarchitecture.domain.models.User
import ru.otus.basicarchitecture.domain.models.UserAddress
import ru.otus.basicarchitecture.domain.models.UserAge
import ru.otus.basicarchitecture.domain.models.UserHobby
import ru.otus.basicarchitecture.domain.models.UserName
import ru.otus.basicarchitecture.domain.models.UserSurname
import ru.otus.basicarchitecture.domain.repository.UserRepository

class UserRepositoryImpl:UserRepository {
    override fun getData():User {
        TODO("Not yet implemented")
    }

    override fun saveUserName(userName: UserName) {
        TODO("Not yet implemented")
    }

    override fun saveUserSurname(userSurname: UserSurname) {
        TODO("Not yet implemented")
    }

    override fun saveUserAge(userAge: UserAge) {
        TODO("Not yet implemented")
    }

    override fun saveUserAddress(userAddress: UserAddress) {
        TODO("Not yet implemented")
    }

    override fun saveUserHobby(userHobby: UserHobby) {
        TODO("Not yet implemented")
    }


}