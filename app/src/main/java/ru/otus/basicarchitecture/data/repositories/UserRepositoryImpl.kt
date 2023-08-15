package ru.otus.basicarchitecture.data.repositories

import android.content.Context
import android.util.Log
import ru.otus.basicarchitecture.domain.entities.User
import ru.otus.basicarchitecture.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val context: Context): UserRepository {
    override fun editUserProfile(user: User) {
        TODO("Not yet implemented")
    }

    override fun downloadUserProfile(): User {
        TODO("Not yet implemented")
    }

    override fun saveUser() {
       Log.d("URImpl", context.toString())
    }

    override fun downloadAllUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override fun deleteUser(user: User) {
        TODO("Not yet implemented")
    }


}