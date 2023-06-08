package ru.otus.basicarchitecture.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.otus.basicarchitecture.domain.usecases.GetDataUseCase
import ru.otus.basicarchitecture.domain.usecases.date_usecases.PickDateUseCase
import ru.otus.basicarchitecture.domain.usecases.SaveDataUseCase
import ru.otus.basicarchitecture.domain.usecases.date_usecases.GetDateUseCase
import ru.otus.basicarchitecture.domain.usecases.date_usecases.ValidationUseCase
import ru.otus.basicarchitecture.presentation.fragments.Fragment1
import ru.otus.basicarchitecture.presentation.viewmodels.ActivityVMFactory
import ru.otus.basicarchitecture.presentation.viewmodels.FragmentVMFactory


@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext():Context{
        return context
    }



    @Provides
    fun provideFragment1VMFactory(
        getDataUseCase:GetDataUseCase,
        saveDataUseCase:SaveDataUseCase,
        pickDateUseCase: PickDateUseCase,
        validationUseCase: ValidationUseCase,
        getDateUseCase: GetDateUseCase
    )
    :FragmentVMFactory{
        return FragmentVMFactory(
            getDataUseCase,
            saveDataUseCase,
            pickDateUseCase,
            validationUseCase,
            getDateUseCase

        )
    }

    @Provides
    fun provideActivityVMFactory():ActivityVMFactory{
        return ActivityVMFactory()
    }

    @Provides
    fun provideFragment1():Fragment1{
        return Fragment1()
    }

}