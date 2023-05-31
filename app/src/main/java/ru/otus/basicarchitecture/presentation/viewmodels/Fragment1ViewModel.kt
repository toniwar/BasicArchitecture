package ru.otus.basicarchitecture.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.otus.basicarchitecture.data.UserRepositoryImpl
import ru.otus.basicarchitecture.data.local_storage.WizardCatch
import ru.otus.basicarchitecture.domain.models.User
import ru.otus.basicarchitecture.domain.models.UserAge
import ru.otus.basicarchitecture.domain.models.UserName
import ru.otus.basicarchitecture.domain.models.UserSurname
import ru.otus.basicarchitecture.domain.models.ViewModelData
import ru.otus.basicarchitecture.domain.usecases.GetDataUseCase
import ru.otus.basicarchitecture.domain.usecases.SaveDataUseCase

class Fragment1ViewModel(context: Context):ViewModel() {
    private val wizardCatch by lazy { WizardCatch(context) }
    private val userRepository by lazy { UserRepositoryImpl(wizardCatch) }
    private val getDataUseCase by lazy { GetDataUseCase(userRepository) }
    private val saveDataUseCase by lazy { SaveDataUseCase(userRepository) }
    private val mutableOutputData = MutableLiveData<User>()
    val outputData:LiveData<User>get() = mutableOutputData
    val inputData = MutableLiveData<List<ViewModelData>>()

    init {
        loadData()

    }


    private fun loadData(){
        mutableOutputData.value = getDataUseCase.getUser()
    }

    fun saveData(){
        inputData.value.apply {
            val userList = this
            saveDataUseCase.apply {
                setUserName(userList?.get(0) as UserName)
                setUserSurname(userList[1] as UserSurname)
                setUserAge(userList[2] as UserAge)
            }

        }
        loadData()
    }

    companion object{
        fun factory(context: Context) = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return Fragment1ViewModel(context.applicationContext) as T
            }
        }
    }


}