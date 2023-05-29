package ru.otus.basicarchitecture.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.otus.basicarchitecture.VMStateFlags

class ActivityViewModel: ViewModel() {

    private val mutableLiveData = MutableLiveData<VMStateFlags>()
    val liveData:LiveData<VMStateFlags> get() = mutableLiveData

    init {
        mutableLiveData.value = VMStateFlags.FRAGMENT_1
    }


}