package ru.otus.basicarchitecture.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.otus.basicarchitecture.domain.usecases.GetDataUseCase
import ru.otus.basicarchitecture.domain.usecases.date_usecases.PickDateUseCase
import ru.otus.basicarchitecture.domain.usecases.SaveDataUseCase
import ru.otus.basicarchitecture.domain.usecases.date_usecases.GetDateUseCase
import ru.otus.basicarchitecture.domain.usecases.date_usecases.ValidationUseCase

class Fragment1VMFactory(
    private val getDataUseCase: GetDataUseCase,
    private val saveDataUseCase: SaveDataUseCase,
    private val pickDateUseCase: PickDateUseCase,
    private val validationUseCase: ValidationUseCase,
    private val getDateUseCase: GetDateUseCase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Fragment1ViewModel(
            getDataUseCase,
            saveDataUseCase,
            pickDateUseCase,
            validationUseCase,
            getDateUseCase
        ) as T
    }
}

class Fragment2VMFactory(
    private val getDataUseCase: GetDataUseCase,
    private val saveDataUseCase: SaveDataUseCase,
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Fragment2ViewModel(
            getDataUseCase, saveDataUseCase

        ) as T
    }
}

class ActivityVMFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ActivityViewModel() as T
    }
}