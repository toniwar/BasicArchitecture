package ru.otus.basicarchitecture.domain.repositories

import kotlinx.coroutines.flow.Flow


interface DataStoreManager {

    suspend fun saveUserInfoToDataStore(key: UserInfoKeys, value: String)
    suspend fun saveAppStateToDataStore(key: AppStatesKeys, value: Boolean)
    fun getUserInfoFromDataStore(key: UserInfoKeys): Flow<String?>
    fun getStateFromDataStore(key: AppStatesKeys): Flow<Boolean>


    companion object Keys{

        enum class UserInfoKeys(keyName: String){

            NAME("user_name"),
            SURNAME("user_surname"),
            AGE("user_age"),
            ADDRESS("user_address"),
            INTERESTS("user_interests")
        }


        enum class AppStatesKeys(keyName: String){

            IS_USERS_IN_DB("is_user_in_db"),
            IS_APP_CLOSED_SUDDENLY("is_app_closed_suddenly")

        }

    }

}