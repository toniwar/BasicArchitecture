package ru.otus.basicarchitecture.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.otus.basicarchitecture.domain.usecases.GetDataUseCase
import ru.otus.basicarchitecture.domain.usecases.SaveDataUseCase

class FragmentVMFactory(
    private val getDataUseCase: GetDataUseCase,
    private val saveDataUseCase: SaveDataUseCase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Fragment1ViewModel(getDataUseCase, saveDataUseCase) as T
    }
}

class ActivityVMFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ActivityViewModel() as T
    }
}