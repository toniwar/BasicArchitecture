package ru.otus.basicarchitecture.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.otus.basicarchitecture.domain.models.User
import ru.otus.basicarchitecture.domain.models.UserAge
import ru.otus.basicarchitecture.domain.models.UserName
import ru.otus.basicarchitecture.domain.models.UserSurname
import ru.otus.basicarchitecture.domain.models.ViewModelData
import ru.otus.basicarchitecture.domain.usecases.GetDataUseCase
import ru.otus.basicarchitecture.domain.usecases.SaveDataUseCase


class Fragment1ViewModel(
    private val getDataUseCase: GetDataUseCase,
    private val saveDataUseCase: SaveDataUseCase
):ViewModel() {

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



}