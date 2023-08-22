package ru.otus.basicarchitecture.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.otus.basicarchitecture.domain.entities.UserInfo
import ru.otus.basicarchitecture.domain.repositories.DataStoreManager
import ru.otus.basicarchitecture.domain.use_cases.InputDataCheckerUseCase
import ru.otus.basicarchitecture.presentation.date_manage.DateChecker
import javax.inject.Inject

class  StartRegistrationFragmentVM @Inject constructor(
    private val inputDataCheckerUseCase: InputDataCheckerUseCase,
): ViewModel() {



    @Inject
    lateinit var dataStoreManager: DataStoreManager



    private lateinit var userInfo: UserInfo


    private val mutableState = MutableStateFlow<UserInfo?>(null)
    val state: StateFlow<UserInfo?> get() = mutableState.asStateFlow()




    fun validation(year: String, data: List<String?>): Boolean{
        return inputDataCheckerUseCase.checkInputData(data) && DateChecker.ageValidator(year)

    }

    fun saveUserInfoToDataStore(key: DataStoreManager.Keys.UserInfoKeys, value: String){
        viewModelScope.launch {
            dataStoreManager.saveUserInfoToDataStore(key, value)
        }
    }

    suspend fun getUserInfoFromDataStore(keys: Set<DataStoreManager.Keys.UserInfoKeys>){
        val userInfoMap =
            mutableMapOf<DataStoreManager.Keys.UserInfoKeys, String>()
        keys.forEach {key->
            dataStoreManager.getUserInfoFromDataStore(key).collect {
                userInfoMap[key] = it?:""
            }

        }
        val newUserInfo = userInfo.copy(info = userInfoMap)
        userInfo = newUserInfo
        mutableState.value = userInfo

    }


}