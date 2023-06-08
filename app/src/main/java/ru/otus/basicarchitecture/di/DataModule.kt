package ru.otus.basicarchitecture.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.otus.basicarchitecture.data.DatePickerImpl
import ru.otus.basicarchitecture.data.UserRepositoryImpl
import ru.otus.basicarchitecture.data.local_storage.UserStorage
import ru.otus.basicarchitecture.data.local_storage.WizardCatch
import ru.otus.basicarchitecture.domain.repository.DatePicker
import ru.otus.basicarchitecture.domain.repository.UserRepository

@Module
class DataModule {

    @Provides
    fun provideUserStorage(context: Context):UserStorage{
        return WizardCatch(context)
    }

    @Provides
    fun providesUserRepository(userStorage:UserStorage):UserRepository{
        return UserRepositoryImpl(userStorage)
    }

    @Provides
    fun provideDatePicker():DatePicker{
        return DatePickerImpl
    }


}