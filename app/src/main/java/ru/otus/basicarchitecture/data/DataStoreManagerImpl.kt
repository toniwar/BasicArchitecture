package ru.otus.basicarchitecture.data

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import ru.otus.basicarchitecture.domain.repositories.DataStoreManager
import javax.inject.Inject


class DataStoreManagerImpl @Inject constructor(context: Context): DataStoreManager {



    private val userDataStore = context.dataStore


    override suspend fun saveUserInfoToDataStore(key: DataStoreManager.Keys.UserInfoKeys, value: String) {
        val dataStoreKey = stringPreferencesKey(key.name)
        userDataStore.edit {
            it[dataStoreKey] = value
        }

    }

    override suspend fun saveAppStateToDataStore(key: DataStoreManager.Keys.AppStatesKeys, value: Boolean) {
        val dataStoreKey = booleanPreferencesKey(key.name)
        userDataStore.edit {
            it[dataStoreKey] = value
        }

    }


    override fun getUserInfoFromDataStore(key: DataStoreManager.Keys.UserInfoKeys): Flow<String> {
        val datastoreKey = stringPreferencesKey(key.name)
        return userDataStore.data
            .catch {
                Log.w("DataStoreException", it.message.toString())
                emit(emptyPreferences())
            }
            .map {
                it[datastoreKey]?: ""
            }

    }

    override fun getStateFromDataStore(key: DataStoreManager.Keys.AppStatesKeys): Flow<Boolean> {

        val datastoreKey = booleanPreferencesKey(key.name)
        return userDataStore.data
            .catch {
                Log.w("DataStoreException", it.message.toString())
                emit(emptyPreferences())
            }
            .map {
                it[datastoreKey]?: false
            }

    }
    companion object{
        private const val USER_DATA_STORE = "user_data_store"
        private val Context.dataStore  by preferencesDataStore(USER_DATA_STORE)
    }


}
