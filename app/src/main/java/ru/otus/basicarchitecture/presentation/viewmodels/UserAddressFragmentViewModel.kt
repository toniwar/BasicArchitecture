package ru.otus.basicarchitecture.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.otus.basicarchitecture.domain.models.NetworkRequest
import ru.otus.basicarchitecture.domain.models.NetworkResult
import ru.otus.basicarchitecture.domain.models.User
import ru.otus.basicarchitecture.domain.models.UserAddress
import ru.otus.basicarchitecture.domain.models.ViewModelData
import ru.otus.basicarchitecture.domain.usecases.data_use_cases.GetDataUseCase
import ru.otus.basicarchitecture.domain.usecases.data_use_cases.SaveDataUseCase
import ru.otus.basicarchitecture.domain.usecases.network_use_cases.GetNetworkResponseUseCase
import ru.otus.basicarchitecture.domain.usecases.network_use_cases.SendNetworkRequestUseCase

class UserAddressFragmentViewModel(

    private val getDataUseCase: GetDataUseCase,
    private val saveDataUseCase: SaveDataUseCase,
    private val getNetworkResponseUseCase: GetNetworkResponseUseCase,
    private val sendNetworkRequestUseCase: SendNetworkRequestUseCase


    ): ViewModel() {

    private val _state = MutableStateFlow<NetworkResult?>(null)
    val state = _state.asStateFlow()

    private val mutableOutputData = MutableLiveData<User>()
    val outputData: LiveData<User>
        get() = mutableOutputData
    val inputData = MutableLiveData<ViewModelData>()

    init {
        loadData()

    }

    private fun loadData(){
        mutableOutputData.value = getDataUseCase.getUser()
    }


    fun saveData() {
        inputData.value.apply {
            val userAddress = this as UserAddress
            saveDataUseCase.setUserAddress(userAddress)
        }
        loadData()
    }


    fun sendRequest(request: NetworkRequest){
        sendNetworkRequestUseCase.sendRequest(request)

        getResult()
    }



    private fun getResult() {
        viewModelScope.launch {

            getNetworkResponseUseCase.getResponse().collect{
                _state.value = it

            }
        }
    }
}