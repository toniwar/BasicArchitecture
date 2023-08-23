package ru.otus.basicarchitecture.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.otus.basicarchitecture.domain.repositories.DataStoreManager
import ru.otus.basicarchitecture.domain.use_cases.InputDataCheckerUseCase
import ru.otus.basicarchitecture.presentation.date_manage.DateChecker
import javax.inject.Inject

class  StartRegistrationFragmentVM @Inject constructor(
    private val inputDataCheckerUseCase: InputDataCheckerUseCase,
): ViewModel() {



    @Inject
    lateinit var dataStoreManager: DataStoreManager



    private val mutableState =
        MutableStateFlow<Pair<DataStoreManager.Keys.UserInfoKeys, String?>?>(null)
    val state: StateFlow<Pair<DataStoreManager.Keys.UserInfoKeys, String?>?> get() = mutableState.asStateFlow()




    fun checkForEmptyData(data: Map<DataStoreManager.Keys.UserInfoKeys,String?>): Boolean{
        data.forEach {
            saveUserInfoToDataStore(it.key, it.value?:"")
        }
        return inputDataCheckerUseCase.checkInputData(data.values)

    }

    fun checkUserAge(birthYear: String?): Boolean{
        return DateChecker.ageValidator(birthYear)
    }

    private fun saveUserInfoToDataStore(key: DataStoreManager.Keys.UserInfoKeys, value: String){
        viewModelScope.launch {
            dataStoreManager.saveUserInfoToDataStore(key, value)
        }
    }

    fun getUserInfoFromDataStore(key: DataStoreManager.Keys.UserInfoKeys){

            dataStoreManager.getUserInfoFromDataStore(key)
                .onEach{
               mutableState.value = Pair(key,it)
            }
                .launchIn(viewModelScope)


    }


}