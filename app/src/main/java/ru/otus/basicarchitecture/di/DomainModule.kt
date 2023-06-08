package ru.otus.basicarchitecture.di

import dagger.Module
import dagger.Provides
import ru.otus.basicarchitecture.domain.repository.DatePicker
import ru.otus.basicarchitecture.domain.repository.UserRepository
import ru.otus.basicarchitecture.domain.usecases.GetDataUseCase
import ru.otus.basicarchitecture.domain.usecases.date_usecases.PickDateUseCase
import ru.otus.basicarchitecture.domain.usecases.SaveDataUseCase
import ru.otus.basicarchitecture.domain.usecases.date_usecases.GetDateUseCase
import ru.otus.basicarchitecture.domain.usecases.date_usecases.ValidationUseCase

@Module
class DomainModule {

    @Provides
    fun provideGetDataUseCase(userRepository: UserRepository):GetDataUseCase{
        return GetDataUseCase(userRepository)
    }

    @Provides
    fun provideSaveDataUseCase(userRepository: UserRepository):SaveDataUseCase{
        return SaveDataUseCase(userRepository)
    }

    @Provides
    fun providePickDateUseCase(datePicker: DatePicker): PickDateUseCase {
        return PickDateUseCase(datePicker)
    }

    @Provides
    fun provideValidationUseCase(datePicker: DatePicker): ValidationUseCase{
        return  ValidationUseCase(datePicker)
    }

    @Provides
    fun provideGetDateUseCase(datePicker: DatePicker): GetDateUseCase{
        return  GetDateUseCase(datePicker)
    }
}